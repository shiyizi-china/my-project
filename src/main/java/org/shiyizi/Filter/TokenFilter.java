package org.shiyizi.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.shiyizi.Util.JwtUtils;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();

        if (url.contains("/login")){
            log.info("登录请求，放行：{}", url);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        
        String token = request.getHeader("token");
        
        if (token == null || token.isEmpty()) {
            String authorization = request.getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Bearer ")) {
                token = authorization.substring(7);
            }
        }
        
        if (token == null || token.isEmpty()){
            log.warn("Token 为空，返回 401 - URL: {}", url);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"Token 为空\"}");
            return;
        }
        
        try {
            var claims = JwtUtils.parseToken(token);
            log.info("Token 验证成功 - URL: {}, 用户 ID: {}", url, claims.get("id"));
            filterChain.doFilter(servletRequest,servletResponse);
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


