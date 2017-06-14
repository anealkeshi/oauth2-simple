package me.anilkc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // @formatter:off
      clients.inMemory()
             .withClient("clientA")
             .secret("secreta")
             .scopes("READ")
             .autoApprove(true)
             .accessTokenValiditySeconds(600)
             .refreshTokenValiditySeconds(600)
             .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
             .and()
             .withClient("clientB")
             .secret("secretb")
             .scopes("WRITE")
             .autoApprove(true)
             .accessTokenValiditySeconds(600)
             .refreshTokenValiditySeconds(600)
             .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code");
    // @formatter:on
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // @formatter:off
    endpoints.tokenStore(tokenStore());
    // @formatter:on
  }


  @Bean
  TokenStore tokenStore() {
    return new InMemoryTokenStore();
  }

}
