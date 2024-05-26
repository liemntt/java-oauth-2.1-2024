package dev.thanhliem.oauth.models.responses;

import dev.thanhliem.oauth.constants.ApplicationType;
import dev.thanhliem.oauth.models.jsonb.ApplicationDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {
    private String name;
    private ApplicationType type;
    private String clientId;
    private Long userId;
    private ApplicationDetails details;
    private Long id;
    private String createdBy;
    private OffsetDateTime createdDate;
    private String updatedBy;
    private OffsetDateTime updatedDate;
}
