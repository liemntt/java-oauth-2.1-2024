package dev.thanhliem.oauth.models.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPayload {
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
}