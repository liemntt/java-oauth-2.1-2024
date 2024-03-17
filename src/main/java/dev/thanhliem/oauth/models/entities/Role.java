package dev.thanhliem.oauth.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;
    private String name;
    private String createdBy;
    private OffsetDateTime createdDate;
    private String updatedBy;
    private OffsetDateTime updatedDate;
}
