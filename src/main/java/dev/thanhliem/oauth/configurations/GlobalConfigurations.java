package dev.thanhliem.oauth.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class GlobalConfigurations {

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("[GlobalConfigurations] Initializing password encoder ");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
