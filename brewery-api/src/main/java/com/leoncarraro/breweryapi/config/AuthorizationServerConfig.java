package com.leoncarraro.breweryapi.config;

import com.leoncarraro.breweryapi.config.property.ApplicationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final ApplicationProperty applicationProperty;

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenEnhancer tokenEnhancer;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager,
                                     BCryptPasswordEncoder bCryptPasswordEncoder,
                                     TokenStore tokenStore, JwtAccessTokenConverter jwtAccessTokenConverter,
                                     TokenEnhancer tokenEnhancer, ApplicationProperty applicationProperty) {

        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenEnhancer = tokenEnhancer;
        this.applicationProperty = applicationProperty;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("Angular")
                .secret(bCryptPasswordEncoder.encode(
                        applicationProperty.getApplicationSecurity().getOauthClientPassword()))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(applicationProperty.getApplicationSecurity()
                        .getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(applicationProperty.getApplicationSecurity()
                        .getRefreshTokenValiditySeconds());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));

        endpoints.reuseRefreshTokens(false)
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
    }

}
