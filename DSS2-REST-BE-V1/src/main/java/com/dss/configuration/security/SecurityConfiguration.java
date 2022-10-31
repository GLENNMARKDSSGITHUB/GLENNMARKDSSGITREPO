package com.dss.configuration.security;

import com.dss.service.auth.AuthUserDetailsService;
import com.dss.service.auth.DssUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DssUserDetailsServiceImpl dssUserDetailsService;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Configuration
    @Order(1)
    public class DssLoginSecurityConfiguration extends WebSecurityConfigurerAdapter{
//        @Bean
//        AuthenticationProvider authenticationProvider(){
//            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//            provider.setUserDetailsService(authUserDetailsService);
//            provider.setPasswordEncoder(passwordEncoder());
//            return provider;
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .formLogin()
//                    .loginPage("/API/login.do")
//                    .permitAll();
//        }
    }

    @Configuration
    @Order(2)
    public class DssApiSecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Bean
        AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(dssUserDetailsService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/")
                    .permitAll()
                    .antMatchers("/API/registration")
                    .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                    .antMatchers("/API/movie-catalogue")
                    .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                    .antMatchers("/API/actor")
                    .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                    .antMatchers("/API/reviews")
                    .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER")
                    .antMatchers("/API/actor")
                    .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
        }
    }
}
