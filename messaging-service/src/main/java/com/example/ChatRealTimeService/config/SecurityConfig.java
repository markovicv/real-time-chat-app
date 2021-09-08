package com.example.ChatRealTimeService.config;

import com.example.ChatRealTimeService.filter.HttpFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final HttpFilter httpFilter;

    public SecurityConfig(HttpFilter httpFilter){
        this.httpFilter=httpFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(this.httpFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
