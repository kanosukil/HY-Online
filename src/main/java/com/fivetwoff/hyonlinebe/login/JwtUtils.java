package com.fivetwoff.hyonlinebe.login;

import com.fivetwoff.hyonlinebe.entity.Role;
import com.fivetwoff.hyonlinebe.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/27 - 08:47
 */

public class JwtUtils {
    private static final String SUBJECT = "524&525DZY";
    private static final long EXPIRE = 1000 * 60 * 60 * 24;
    private static final String SECRET = "HYONLINEbe";

    public static String genJsonWebToken(User user, List<Role> role) {
        if (user == null || user.getId() == null || user.getUsername() == null || user.getPassword_hash() == null) {
            return null;
        }
        StringBuilder roleList = new StringBuilder();
        for (Role r : role) {
            roleList.append(r.getRank()).append(",");
        }
        return Jwts.builder()
                .setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("roleRank", roleList.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Claims checkJWT(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
