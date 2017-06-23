package me.anilkc.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


  @Override
  public void configure(HttpSecurity http) throws Exception {
   // @formatter:off
      http
      .requestMatchers().antMatchers("/secure/**")    
      .and()
      .authorizeRequests()
      .antMatchers("/secure/admin").access("#oauth2.hasScope('READ') and hasRole('ROLE_ADMIN')")
      .antMatchers("/secure/**").authenticated();
    // @formatter:on
  }
}
