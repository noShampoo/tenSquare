package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJWT {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("noShampoo")
                .parseClaimsJws( "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjY3ODA3OTAyNDg4NDY1NDA4Iiwic3ViIjoiYmhqIiwiaWF0IjoxNTkxMzQ1NDY4LCJyb2xlcyI6ImFkbWluIiwiZXhwIjoxNTkxMzQ5MDY4fQ.B2JnpvscYL_P6XQkAVGY2jlfQdd87xPLYTULYutKoIg")
                .getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
        System.out.println(claims.get("role"));
    }
}
