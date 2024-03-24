package dev.thanhliem.oauth.services.resources;

import dev.thanhliem.oauth.models.entities.User;
import dev.thanhliem.oauth.models.payloads.ResetPasswordPayload;
import dev.thanhliem.oauth.models.payloads.SignInPayload;
import dev.thanhliem.oauth.models.payloads.SignUpPayload;
import dev.thanhliem.oauth.models.payloads.UserPayload;
import dev.thanhliem.oauth.models.responses.RequestTokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    UserPayload find(long id);

    UserPayload signUp(SignUpPayload payload);

    RequestTokenResponse signIn(SignInPayload payload);

    UserPayload resetPassword(ResetPasswordPayload payload);
}
