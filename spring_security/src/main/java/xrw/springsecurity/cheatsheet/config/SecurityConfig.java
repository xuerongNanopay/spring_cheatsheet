package xrw.springsecurity.cheatsheet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Override default form login
    http.authorizeHttpRequests(
      (authz) -> 
      authz.anyRequest().authenticated()
      // Open up all controller.
      // authz.anyRequest().permitAll()
    )
    .formLogin(withDefaults());
    //.httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("adminAb1")
            .roles("admin")
            .build();
    return new InMemoryUserDetailsManager(user);
  }
}
