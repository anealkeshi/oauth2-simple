package me.anilkc.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  // @formatter:off
    auth.inMemoryAuthentication()
        .withUser("user").password("password").roles("USER")
        .and()
        .withUser("admin").password("password").roles("ADMIN")
        .and()
        .withUser("participant").password("password").roles("PARTICIPANT");
  // @formatter:on
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
  // @formatter:off
    http.csrf().disable()
    .authorizeRequests().antMatchers("/oauth/authorize").authenticated()
    .and()
    .formLogin().permitAll()
    .and()
    .logout()
    .and()
    .authorizeRequests().anyRequest().authenticated();
  // @formatter:on
  }

}
