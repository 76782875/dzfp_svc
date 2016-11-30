package com.rjxx.taxeasy.bizcomm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by admin on 2016/3/8.
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/extractInvoice/**","/tqm/**", "/login", "/login/**", "/assets/**",
                        "/css/**", "/img/**", "/js/**", "/image.jsp", "/zc/**","/*.jsp").permitAll()
                .anyRequest().hasRole("LOGIN_USER")
                .and()
                .formLogin()
                .loginPage("/login/login")
                .failureUrl("/login/login?error=1")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .invalidateHttpSession(true)
                .and().csrf().disable();
    }

    @Autowired
    public void globe_configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
