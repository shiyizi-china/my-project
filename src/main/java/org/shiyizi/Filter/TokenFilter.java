package org.shiyizi.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.shiyizi.Util.JwtUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component  // 推荐使用 @Component 让 Spring 管理，而不是 @WebFilter
@Order(1)   // 确保过滤器优先级
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();

        // ========== 1. 放行 OPTIONS 请求（CORS 预检） ==========
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.debug("OPTIONS 预检请求，直接放行: {}", url);
            // 注意：CORS 头部应由 CorsFilter 或 CorsConfig 添加，这里只需放行
            filterChain.doFilter(request, response);
            return;
        }

        // ========== 2. 放行登录接口 ==========
        if (url.contains("/login")) {
            log.info("登录请求，放行：{}", url);
            filterChain.doFilter(request, response);
            return;
        }

        // ========== 3. 获取 Token（支持 Authorization: Bearer <token> 或 token 头） ==========
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Bearer ")) {
                token = authorization.substring(7);
            }
        }

        if (token == null || token.isEmpty()) {
            log.warn("Token 为空，返回 401 - URL: {}", url);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"Token 为空\"}");
            return;
        }

        // ========== 4. 验证 Token ==========
        try {
            var claims = JwtUtils.parseToken(token);
            log.info("Token 验证成功 - URL: {}, 用户 ID: {}", url, claims.get("id"));
            filterChain.doFilter(request, response);
        } catch (io.jsonwebtoken.SignatureException e) {
            log.error("Token 签名验证失败：{} - URL: {}", e.getMessage(), url);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.error("Token 已过期 - URL: {}", url);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Token 验证失败：{} - URL: {}", e.getMessage(), url);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
