package xrw.springsecurity.cheatsheet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/ping")
@RestController
public class PingController {
  
  @GetMapping
  public String ping() {
    return "PONG: " + LocalDate.now();
  }
}
