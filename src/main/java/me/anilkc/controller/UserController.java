package me.anilkc.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class UserController {

  @GetMapping("/user")
  public @ResponseBody Principal getUser(Principal user) {
    return user;
  }

  @GetMapping("/admin")
  public @ResponseBody Principal getAdmin(Principal admin) {
    return admin;
  }

  @PreAuthorize("#oauth2.hasScope('WRITE')")
  @GetMapping("/participant")
  public @ResponseBody Principal getParticipant(Principal participant) {
    return participant;
  }

}
