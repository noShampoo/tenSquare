package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * authorizeRequests所有security全注解配置的开始，开始需要的权限
         * （该权限分为两部分，1是拦截的路径，2是访问该路径需要的权限）
         *
         *antMatchers("/**")表示拦截的路径
         * permitAll表示任何权限都可以
         *
         *anyRequest()表示任意的请求.authenticated()表示认证后才能访问
         *
         * .and().csrf().disable()固定写法，表示csrf拦截失效
         */
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
