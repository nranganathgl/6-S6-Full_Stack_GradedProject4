package com.gl.employee.security;

import com.gl.employee.exception.CustomAccessDeniedHandler;
import com.gl.employee.service.DomainUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfigurtion {

    private final DomainUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        try {
            httpSecurity.authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/v1/api/employees/")
                    .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/v1/api/employees/role/**", "/v1/api/employees/user/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/api/employees/**", "/v1/api/employees/search/**", "/v1/api/employees/sort**")
                    .permitAll()
                    .antMatchers(HttpMethod.PUT, "/v1/api/employees/**")
                    .permitAll()
                    .antMatchers(HttpMethod.DELETE, "/v1/api/employees/**")
                    .permitAll()
                    .anyRequest().authenticated().and().formLogin()
                    .and().httpBasic()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler())
                    .and().cors().and().csrf().disable()
            ;
            return httpSecurity.build();
        } catch (Exception e) {
            System.out.println("Exception in security: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       PasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService
    ) {
        try {
            return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder)
                    .and()
                    .build()
                    ;
        } catch (Exception e) {
            System.out.println("Exception in AuthenticationManager: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
