package dev.thanhliem.oauth.models.payloads;

public record UpdatePasswordPayload(String oldPassword, String newPassword){
}
