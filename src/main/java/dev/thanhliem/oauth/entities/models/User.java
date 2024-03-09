package dev.thanhliem.oauth.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private LocalDate birthday;
    private String createdBy;
    private ZonedDateTime createdDate;
    private String username;
    private String password;
    private String email;
    private String updatedBy;
    private ZonedDateTime updatedDate;
}
