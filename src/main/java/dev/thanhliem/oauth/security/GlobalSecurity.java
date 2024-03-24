package dev.thanhliem.oauth.security;

import dev.thanhliem.oauth.constants.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class GlobalSecurity {

    private final JwtTokenAuthenticationEntryPoint jwtTokenAuthenticationEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                custom -> custom
                    .requestMatchers(permitRequests())
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .exceptionHandling(s -> s.authenticationEntryPoint(jwtTokenAuthenticationEntryPoint))
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    private String[] permitRequests() {
        return List.of(Endpoints.UserApi.SIGN_UP,
                Endpoints.UserApi.SIGNING,
                Endpoints.UserApi.RESET_PASSWORD).
            toArray(String[]::new);
    }
}
