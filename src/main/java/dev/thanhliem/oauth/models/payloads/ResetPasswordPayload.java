package dev.thanhliem.oauth.models.payloads;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResetPasswordPayload {
    private String usernameOrEmail;
    private LocalDate birthDate;
}
