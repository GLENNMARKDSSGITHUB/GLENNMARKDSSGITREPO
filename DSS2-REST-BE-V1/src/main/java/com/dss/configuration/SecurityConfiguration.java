/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.configuration;

import com.dss.service.userdetails.DssUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class is a security configuration for Login and Authentication
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DssUserDetailsServiceImpl dssUserDetailsService;

    /** Returns a BCryptPasswordEncoder with the strength of 12
     * param strength - the log rounds to use, between 4 and 31 (in this project, I use strength of 12)
     * @return BCryptPasswordEncoder with the strength of 12
     * @see #passwordEncoder()
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /** Performs authentication with the same contract as AuthenticationManager.authenticate(Authentication) .
     * @return a fully authenticated object including credentials.
     * @see #authenticationProvider()
     */
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(dssUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /** Configures the authorization for the URL such as things like if it requires to be authenticated
     *  or if only certain roles can access it etc. It only has effect for those URLs that are processed by
     *  that SecurityFilterChain (i.e. Those URLs that are matched by requestMatchers())
     * @see #configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/API/registration/**")
                .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                .antMatchers("/API/movie-catalogue/**")
                .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                .antMatchers("/API/reviews/**")
                .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/API/actor/**")
                .hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/API/login.do")
                .permitAll()
                .and()
                .httpBasic();
    }
}
