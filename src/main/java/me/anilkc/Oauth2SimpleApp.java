package me.anilkc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Oauth2SimpleApp {

  public static void main(String[] args) {
    SpringApplication.run(Oauth2SimpleApp.class, args);
  }
}
