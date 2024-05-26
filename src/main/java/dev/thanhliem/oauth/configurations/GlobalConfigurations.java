package dev.thanhliem.oauth.configurations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.thanhliem.oauth.properties.AuthenticateProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableConfigurationProperties
public class GlobalConfigurations {

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("[GlobalConfigurations] Initializing password encoder");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConfigurationProperties(prefix = "auth.jwt")
    public AuthenticateProperties authenticateProperties() {
        log.info("[GlobalConfigurations] Initializing authentication properties");
        return new AuthenticateProperties();
    }

    @Bean
    public ObjectMapper jsonMapper() {
        log.info("[GlobalConfigurations] Initializing default json mapper");
        return JsonMapper.builder()
            .findAndAddModules()
            .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, true)
            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
            .build();
    }
}
