package dev.thanhliem.oauth.models.payloads;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SignUpPayload {
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
}
