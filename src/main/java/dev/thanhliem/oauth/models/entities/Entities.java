package dev.thanhliem.oauth.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public abstract class Entities {
    private Long id;
    private String createdBy;
    private OffsetDateTime createdDate;
    private String updatedBy;
    private OffsetDateTime updatedDate;
    private Boolean isDel;
}
