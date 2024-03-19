package dev.thanhliem.oauth.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTokenResponse {
    private String accessToken;
    private String type = "Bearer";
}