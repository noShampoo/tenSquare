package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJWT {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")                   //设置id
                .setSubject("bhj")              //设置名称
                .setIssuedAt(new Date())        //设置时间
                .signWith(SignatureAlgorithm.HS256, "noShampoo")   //设置头(加密方式，加密盐)
                .setExpiration(new Date(new Date().getTime() + 40 * 1000)) //设置过期时间
                .claim("role", "boss");     //自定义key-value
        System.out.println(jwtBuilder.compact());
    }
}
