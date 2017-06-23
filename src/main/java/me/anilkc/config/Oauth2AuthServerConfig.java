package me.anilkc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager auth;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // @formatter:off
      clients.inMemory()
             .withClient("clientA")
             .secret("secreta")
             .scopes("READ")
             .autoApprove(true)
             .accessTokenValiditySeconds(10)
             .refreshTokenValiditySeconds(600)
             .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code", "client_credentials")
             .and()
             .withClient("clientB")
             .secret("secretb")
             .scopes("WRITE")
             .autoApprove(true)
             .accessTokenValiditySeconds(10)
             .refreshTokenValiditySeconds(600)
             .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code", "client_credentials");
    // @formatter:on
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // @formatter:off
    endpoints.tokenStore(tokenStore())
             .authenticationManager(auth);
    // @formatter:on
  }


  @Bean
  TokenStore tokenStore() {
    return new InMemoryTokenStore();
  }

}
