//package pms;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                // Allow public access to specific endpoints (e.g., for POST requests)
//                .antMatchers(HttpMethod.POST, "/api/healthdata").permitAll()
//                // Require authentication for other API endpoints
//                .antMatchers("/api/**").authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .authenticationProvider(new AuthenticationProvider() {
//                    @Override
//                    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                        String username = authentication.getName();
//                        String password = authentication.getCredentials().toString();
//
//                        // Print username and password for testing
//                        System.out.println("Username: " + username);
//                        System.out.println("Password: " + password);
//
//                        // You can add your custom authentication logic here if needed
//
//                        // Create a sample UserDetails object (replace with your actual user retrieval logic)
//                        UserDetails userDetails = User.withUsername(username)
//                                .password(password)
//                                .roles("USER")
//                                .build();
//
//                        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//                    }
//
//                    @Override
//                    public boolean supports(Class<?> authentication) {
//                        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//                    }
//                });
//    }
//}
//

package pms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // Allow public access to specific endpoints (e.g., for POST requests)
                .antMatchers(HttpMethod.POST, "/api/healthdata").permitAll()
                // Require authentication for other API endpoints
                .antMatchers("/api/**").authenticated()
                .and()
                .httpBasic();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
