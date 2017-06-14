package me.anilkc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;



@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


  @Override
  public void configure(HttpSecurity http) throws Exception {
   // @formatter:off
      http
      .requestMatchers().antMatchers("/secure/admin")    
      .and()
      .authorizeRequests()
      .antMatchers("/secure/admin").access("#oauth2.hasScope('READ')");
    // @formatter:on
  }
}
