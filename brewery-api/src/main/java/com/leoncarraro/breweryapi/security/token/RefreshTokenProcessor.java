package com.leoncarraro.breweryapi.security.token;

import com.leoncarraro.breweryapi.config.property.ApplicationProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RefreshTokenProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    private final ApplicationProperty applicationProperty;

    public RefreshTokenProcessor(ApplicationProperty applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getMethod() != null) {
            return methodParameter.getMethod().getName().equals("postAccessToken");
        }

        return false;
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken oAuth2AccessToken,
                                             MethodParameter methodParameter,
                                             MediaType mediaType,
                                             Class<? extends HttpMessageConverter<?>> aClass,
                                             ServerHttpRequest serverHttpRequest,
                                             ServerHttpResponse serverHttpResponse) {

        HttpServletRequest req = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse res = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();

        if (oAuth2AccessToken != null) {
            DefaultOAuth2AccessToken responseBody = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            String refreshToken = oAuth2AccessToken.getRefreshToken().getValue();

            addRefreshTokenOnCookie(refreshToken, req, res);
            removeRefreshTokenFromResponseBody(responseBody);
        }

        return oAuth2AccessToken;
    }

    private void addRefreshTokenOnCookie(String refreshToken, HttpServletRequest req, HttpServletResponse res) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(applicationProperty.getApplicationSecurity().isEnableHttps());
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(604800);
        res.addCookie(refreshTokenCookie);
    }

    private void removeRefreshTokenFromResponseBody(DefaultOAuth2AccessToken responseBody) {
        responseBody.setRefreshToken(null);
    }

}
