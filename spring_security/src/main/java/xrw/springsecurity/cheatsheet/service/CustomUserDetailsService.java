package xrw.springsecurity.cheatsheet.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xrw.springsecurity.cheatsheet.entity.User;
import xrw.springsecurity.cheatsheet.repository.UserRepository;

// for spring security
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    User user = userRepository.findByUsername(username);
    if ( user == null ) throw new UsernameNotFoundException("No Found User: " + username);
    System.out.println("Found user: " + user);
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList())));
  }
  
}
