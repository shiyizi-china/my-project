package org.shiyizi.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

/**
 * JWT令牌工具类
 * 匹配测试代码：HS256算法、密钥c2hpeWl6aQ==、过期时间12小时
 */
public class JwtUtils {

    // 【与测试类完全一致】JWT签名密钥
    private static final String SECRET_KEY = "c2hpeWl6aQ==";

    // 令牌过期时间：12小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 12 * 3600 * 1000;

    /**
     * 生成JWT令牌
     * @param claims 要存储在令牌中的自定义数据（如id、username）
     * @return 生成的JWT字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 签名算法 + 密钥
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 自定义载荷数据
                .addClaims(claims)
                // 过期时间：当前时间 + 12小时
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param token 要解析的JWT字符串
     * @return 解析后的载荷数据Claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                // 设置签名密钥进行验签
                .setSigningKey(SECRET_KEY)
                // 解析令牌
                .parseClaimsJws(token)
                // 获取载荷信息
                .getBody();
    }
}