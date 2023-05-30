# Spring Security(6.1.0)

## Architecture
  - using Servlet Filters / Filter Chain design pattern.
  - Spring Filter entry: DelegatingFilterProxy
  - DelegatingFilterProxy container all Filter beans that are defined in the application.

## Requirement Configuration Bean:
  - ***UserDetailsService***
      - This service tell spring how&where to lookup and check the password
      - password lookup and compare
      - password can be stored in memory/DB/.... depends on system architecture
  - PasswordEncoder
      - define the password encode algorithm like plain text or bcrypt
  - ***SecurityFilterChain(Important)***
      - The place that is used to define the auhortication scheme.
      - eg: ```{url}/ping``` can be accessed by any user
      - eg: ```{url}/admin``` can only be accessed by admin user


## Encode Password
Using DelegatingPasswordEncoder for PasswordEncoder

## TroubleShooting
  - if you are using ```HttpSecurity.formLogin(withDefaults)```, the /login and /logout port will be override