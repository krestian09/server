package com.example.dplm.spring.config;


import com.example.dplm.spring.auth.StatelessAuthFilter;
import com.example.dplm.spring.service.TokenAuthService;
import com.example.dplm.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenAuthService tokenAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(new StatelessAuthFilter(tokenAuthService),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/sign/up", "/sign/in").permitAll()
                .antMatchers( "/dishes","/ingredients").permitAll()
                .anyRequest()
                .authenticated();
    }

//    @Bean
//    public PasswordEncoder bcryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userService)
//                .passwordEncoder(bcryptPasswordEncoder());
//    }

}
