package dev.thanhliem.oauth.models.jsonb;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplicationDetails {
    private Boolean requireConsent;
    private Boolean requirePKCE;
    private String logoUrl;
    private String policyUrl;
    private String termsOfService;
    private List<String> signInUrls;
}
