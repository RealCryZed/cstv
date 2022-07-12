package cstv.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * Configures authentication settings by adding userDetailsService and passwordEncoder.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }


    /**
     * Configures url settings.
     * Sets login and logout pages.
     * Adds rememberMe() entity.
     * Checks if particular pages have "ADMIN" and "USER" authorities.
     * @param http element that gets all the url settings
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/my-profile", "/my-profile/**", "/admin", "/admin/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/", "/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(26400);
    }
}