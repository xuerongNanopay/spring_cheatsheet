# Spring Security(6.1.0)

## Spring Security Servlet Request Flow Architecture
  - using Servlet Filters / Filter Chain design pattern.
  - Spring Filter entry: DelegatingFilterProxy
  - DelegatingFilterProxy container/get Filter beans that are defined in the application.
  - DelegatingFilterProxy is using FilterChainProxy to manager security filters
    - DelegatingFilterProxy -> FilterChainProxy -> SecurityFilterChain -> Filters...
  - ***FilterChainProxy*** is mapping url to SecurityFilterChain, only invoke first match SecurityFilterChain.
  - ***ExceptionTranslationFilter inside SecurityFilterChain*** catch error from ```AccessDeniedException``` and ```AuthenticationException``` to the http response
  - ***RequestCache*** 

  - 
## Authentication Architecture:
  - SecurityContextHolder -> SecurityContext -> Authentication -> {Principle, Credential, Authorities}
  - ***SecurityContextHolder*** is where Spring Security stores the details of who is authenticated
      ```
        // This piece of code demonstrate the relationship between SecurityContextHolder, SecurityContext, and Authentication
        SecurityContext context = SecurityContextHolder.createEmptyContext(); 
        Authentication authentication = new TestingAuthenticationToken("username", "password", "ROLE_USER"); 
        context.setAuthentication(authentication); 
        SecurityContextHolder.setContext(context);
      ```
  - ***Authentication*** contains: principal, credentials, authorities

## Requirement Configuration Bean:
  - ***UserDetailsService***
      - This service tell spring how&where to lookup and check the password
      - password lookup and compare
      - password can be stored in memory/DB/.... depends on system architecture
      - Eg: InMemoryUserDetailsManager, JdbcUserDetailsManager
  - PasswordEncoder
      - define the password encode algorithm like plain text or bcrypt
  - ***SecurityFilterChain(Important)***
      - The place that is used to define the auhortication scheme.
      - eg: ```{url}/ping``` can be accessed by any user
      - eg: ```{url}/admin``` can only be accessed by admin user

## Key Terms:
  - username
  - password
  - role: authorize controller by different role

## Encode Password
 - Using DelegatingPasswordEncoder for PasswordEncoder

## TroubleShooting
  - if you are using ```HttpSecurity.formLogin(withDefaults)```, the /login and /logout port will be override