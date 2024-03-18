package dev.thanhliem.oauth.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthenticateProperties {

    private boolean enabled = true;
    private String secretKey;
    private long expiredTime;
}
