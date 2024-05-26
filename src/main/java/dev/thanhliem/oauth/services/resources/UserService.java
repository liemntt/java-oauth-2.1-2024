package dev.thanhliem.oauth.services.resources;

import dev.thanhliem.oauth.models.entities.User;
import dev.thanhliem.oauth.models.payloads.*;
import dev.thanhliem.oauth.models.responses.RequestTokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    UserPayload find(long id);

    UserPayload signUp(SignUpPayload payload);

    RequestTokenResponse signIn(SignInPayload payload);

    UserPayload resetPassword(ResetPasswordPayload payload);

    UserPayload updatePassword(Long userId, UpdatePasswordPayload payload);

    Optional<User> findByUsername(String username);
}
