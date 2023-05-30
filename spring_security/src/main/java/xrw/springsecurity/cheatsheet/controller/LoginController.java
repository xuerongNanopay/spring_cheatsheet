package xrw.springsecurity.cheatsheet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {
  
  @GetMapping
  public String login() {
    return "Please Login";
  }
}
