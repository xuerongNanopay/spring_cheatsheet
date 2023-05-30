package xrw.springsecurity.cheatsheet.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/logout")
@RestController
public class LogoutController {
  
  @GetMapping
  public String logout() {
    return "loginout: " + LocalDate.now();
  }
}
