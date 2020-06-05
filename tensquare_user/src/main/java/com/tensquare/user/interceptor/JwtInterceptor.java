package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 在这里做的不是具体操作，对所有的请求都会拦截，查看其头部，只有当其头部包含Authorization的时候
         * 才会去检查其token的角色是否正确
         */

        String header = request.getHeader("Authorization");
        if (header != null && !("").equals(header) && header.startsWith("Bearer ")) {
            try {
                Claims claims = jwtUtil.parseJWT(header.substring(7));
                if (claims != null) {
                    if ("admin".equals(claims.get("roles"))) {
                        request.setAttribute("admin_claims", claims);
                    } else if ("user".equals(claims.get("roles"))) {
                        request.setAttribute("user_claims", claims);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("令牌不正确");
            }
        }
        return true;
    }
}
