package xrw.springsecurity.cheatsheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import jakarta.servlet.ServletException;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // Override default form login
    http.authorizeHttpRequests(
      (authz) -> 

      authz
        .requestMatchers("/ping").permitAll()
        .requestMatchers("/admin").hasRole("ADMIN")
        // .requestMatchers("/client").hasRole("AA")
        .anyRequest().authenticated()
      // Open up all controller.
      // authz.anyRequest().permitAll()
    )
    .formLogin((formLogin) ->
      formLogin
      .usernameParameter("username")
      .passwordParameter("password")
      .failureHandler(new AuthenticationFailureHandler() {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
          response.getWriter().println("Fail ...");
        }
      })
    );
    // .failureHandler(new AuthenticationFailureHandler() {
    //   @Override
    //   public void onAuthenticationFailure(HttpRequest request, HttpResponse response, AuthenticationException exception) throws IOException, ServletException {

    //   }
    // });
    // .rememberMe(remember -> remember.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60));
    // .httpBasic(withDefaults());
    return http.build();
  }

  // @Bean
  // public UserDetailsService userDetailsService() {
  //   // In production, we should get all records from DB. We should not inject user in the code.
  //   UserDetails user = User.withDefaultPasswordEncoder()
  //           .username("admin")
  //           .password("adminAb1")
  //           .roles("ADMIN")
  //           .build();
  //   System.out.println(user.getUsername() + " : " + user.getPassword());
  //   return new InMemoryUserDetailsManager(user);
  // }

  // @Bean
  //  // There is deault DataSource bean in JPA. But always can customize by yourself.
      // Comment out CustomUserDetailsService.java before using this one.
  // public UserDetailsService userDetailsService(DataSource dataSource) {
  //   JdbcUserDetailsManager users =new JdbcUserDetailsManager(dataSource);
  //   try {
  //     UserDetails admin = users.loadUserByUsername("admin");
  //     System.out.println("***Found Admin***");
  //   } catch ( UsernameNotFoundException e ) {
  //     // Inject a user for demo
  //     UserDetails admin = User.withDefaultPasswordEncoder()
  //                         .username("admin")
  //                         .password("adminAb1")
  //                         .roles("ADMIN", "USER")
  //                         .build();
  //     users.createUser(admin);
  //     System.out.println("***Create Admin***");
  //   }
  //   return users;
  // }

  // Remember Me service setup.
  @Autowired
  private DataSource dataSource;
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
    jdbcTokenRepositoryImpl.setDataSource(dataSource);
    //jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
    return jdbcTokenRepositoryImpl;
  } 


  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    String idForEncode = "bcrypt";
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    // Support legacy encoder
    encoders.put("sha256", new StandardPasswordEncoder());
    encoders.put("noop", NoOpPasswordEncoder.getInstance());

    return new DelegatingPasswordEncoder(idForEncode, encoders);
  }
}
