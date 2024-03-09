package dev.thanhliem.oauth.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;
    private String name;
    private String createdBy;
    private ZonedDateTime createdDate;
    private String updatedBy;
    private ZonedDateTime updatedDate;
}
