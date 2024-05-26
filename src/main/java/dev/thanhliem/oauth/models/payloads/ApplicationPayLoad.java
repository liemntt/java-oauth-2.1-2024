package dev.thanhliem.oauth.models.payloads;

import dev.thanhliem.oauth.constants.ApplicationType;
import dev.thanhliem.oauth.models.jsonb.ApplicationDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationPayLoad {
    private String name;
    private ApplicationType type;
    private String clientId;
    private String clientSecret;
    private Long userId;
    private ApplicationDetails details;
    private Long id;
    private String createdBy;
    private OffsetDateTime createdDate;
    private String updatedBy;
    private OffsetDateTime updatedDate;
    private Boolean isDel;
}
