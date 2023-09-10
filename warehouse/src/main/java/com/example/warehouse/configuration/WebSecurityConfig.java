package com.example.warehouse.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/login").permitAll()
                .antMatchers("/api/car/**").permitAll()
                .antMatchers("/*", "/images/**", "/scripts/**", "/swagger-ui/**","/v3/**", "/styles/**", "/assets/**", "/h2-console", "/h2", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/newPassword", "/api/mspData/create", "/api/contact/email").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/resetPassword").permitAll()
                .antMatchers(HttpMethod.GET, "/api/mspData/check/**", "/api/reCaptcha/**", "/api/specialist/file/**").permitAll()
                .antMatchers("/api/part/a").permitAll()
                .antMatchers("/api/part/**").permitAll()
                .antMatchers("/api/part/**").permitAll()
                .antMatchers("/api/location/**").permitAll()
                .antMatchers("/api/user/user?page=1&size=30").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/part/item").permitAll()
                .antMatchers("/api/part/worker/{id}").permitAll()
                .antMatchers("/api/part/item2").permitAll()
                .antMatchers("/api/part/worker/{id}").permitAll()
                .antMatchers("/api/auth/{id}/accident/list").permitAll()
                .antMatchers("/api/part/part/list/new").permitAll()
                .antMatchers("/api/part/worker/{id}").permitAll()
                .antMatchers("/api/part/location").permitAll()
                .antMatchers("/api/**/**").permitAll()
                .antMatchers("/api/transaction/**").permitAll()
                .antMatchers("/api/part/page").permitAll()
                .antMatchers("/api/login/save").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/register/new/password").permitAll()
                .antMatchers("/api/stock/**").permitAll()
                .antMatchers("/api/location/list/**").permitAll()
                .antMatchers("/api/human/**").permitAll()
                .antMatchers("/api/auth/**").authenticated()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}