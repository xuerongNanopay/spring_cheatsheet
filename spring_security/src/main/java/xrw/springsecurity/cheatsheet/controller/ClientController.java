package xrw.springsecurity.cheatsheet.controller;

import java.time.LocalDate;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/client")
@RestController
public class ClientController {
  
  @GetMapping
  @PreAuthorize("hasRole('USER')")
  public String helloClient() {
    return "This is Client: " + LocalDate.now();
  }
}
