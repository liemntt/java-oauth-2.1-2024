package dev.thanhliem.oauth.models.entities;

import dev.thanhliem.oauth.constants.ApplicationType;
import dev.thanhliem.oauth.models.jsonb.ApplicationDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application extends Entities {

    private String name;
    private ApplicationType type;
    private String clientId;
    private String clientSecret;
    private Long userId;
    private ApplicationDetails details;
}
