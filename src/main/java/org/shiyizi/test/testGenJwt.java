package org.shiyizi.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Test
public class testGenJwt {
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "shiyizi");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "c2hpeWl6aQ==")
                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }
    public void test2()throws  Exception {
         String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJzaGl5aXppIiwiZXhwIjoxNzc1MjQ2ODY1fQ.dm0er6cGx6K1ptR2JIrJ9JuHGcTuwUlkaZUqRXUHJy4";
        Claims claims = Jwts.parser()
                .setSigningKey("c2hpeWl6aQ==")
                .parseClaimsJws(jwt)
                .getBody();
                System.out.println(claims);
    }
}
