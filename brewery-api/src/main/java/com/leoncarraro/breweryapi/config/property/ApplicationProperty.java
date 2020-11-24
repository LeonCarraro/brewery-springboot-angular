package com.leoncarraro.breweryapi.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "brewery")
@Getter
public class ApplicationProperty {

    private final S3Security s3Security = new S3Security();
    private final ApplicationSecurity applicationSecurity = new ApplicationSecurity();

    @Getter
    @Setter
    public static class S3Security {

        private String accessKey;
        private String secretKey;
        private String region;
        private String bucket;

    }

    @Getter
    @Setter
    public static class ApplicationSecurity {

        private String oauthClientPassword;
        private String jwtSigningKey;

        private Integer accessTokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;
        private String allowedOrigin;
        private boolean enableHttps;

    }

}
