package dev.thanhliem.oauth.services.resources.impl;

import dev.thanhliem.oauth.constants.Constants;
import dev.thanhliem.oauth.constants.ErrorCodes;
import dev.thanhliem.oauth.constants.ErrorMessages;
import dev.thanhliem.oauth.exceptions.ApplicationException;
import dev.thanhliem.oauth.exceptions.BadRequestException;
import dev.thanhliem.oauth.exceptions.ResourceNotFoundException;
import dev.thanhliem.oauth.mappers.UserMapper;
import dev.thanhliem.oauth.models.entities.User;
import dev.thanhliem.oauth.models.payloads.ResetPasswordPayload;
import dev.thanhliem.oauth.models.payloads.SignInPayload;
import dev.thanhliem.oauth.models.payloads.SignUpPayload;
import dev.thanhliem.oauth.models.payloads.UserPayload;
import dev.thanhliem.oauth.models.responses.RequestTokenResponse;
import dev.thanhliem.oauth.properties.AuthenticateProperties;
import dev.thanhliem.oauth.repositories.mappers.RoleRepository;
import dev.thanhliem.oauth.repositories.mappers.UserRepository;
import dev.thanhliem.oauth.security.JwtTokenProvider;
import dev.thanhliem.oauth.services.resources.UserService;
import dev.thanhliem.oauth.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider provider;
    private final AuthenticateProperties authenticateProperties;

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public UserPayload find(long id) {
        User user = repository.find(id);
        if (user == null) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        return mapper.toPayload(user);
    }

    @Override
    public UserPayload signUp(SignUpPayload payload) {
        var isExistWrapper = repository.isExistUsernameOrEmail(payload.getUsername(), payload.getEmail());
        if (isExistWrapper.isPresent()) {
            throw new ApplicationException(ErrorCodes.USERNAME_EXISTED, "Username %s or email %s existed".formatted(payload.getUsername(), payload.getEmail()));
        }
        User user = mapper.toUser(payload);
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        var role = roleRepository.findByName(Constants.Roles.USER)
                .orElseThrow(() -> new ApplicationException(ErrorCodes.ROLES_NOT_FOUND, "Default roles not found."));

        repository.signUp(user);
        repository.insertRelationship(user.getId(), role.getId());
        log.info("[UserService.signUp] created user: {}.", Utils.toJson(user));
        return mapper.toPayload(user);
    }

    @Override
    public RequestTokenResponse signIn(SignInPayload payload) {
        if (Utils.nullOrBlank(payload.usernameOrEmail())) {
            throw new UsernameNotFoundException("Username or email cannot be null or blank");
        }
        if (Utils.nullOrBlank(payload.password())) {
            throw new ApplicationException(ErrorCodes.INVALID_PAYLOAD, "Password cannot be null or blank");
        }
        var mayBeUser = repository.findByUsernameOrEmail(payload.usernameOrEmail());
        if (mayBeUser.isEmpty()) {
            throw new UsernameNotFoundException("Invalid Username/email");
        }
        var user = mayBeUser.get();
        if (passwordEncoder.matches(payload.password(), user.getPassword())) {
            var resp = new RequestTokenResponse();
            var token = provider.generate(user.getUsername());
            resp.setAccessToken(token);
            long duration = Duration.ofMillis(authenticateProperties.getExpiredTime()).toSeconds();
            resp.setExpiresIn(String.valueOf(duration));
            return resp;
        }
        throw new ApplicationException(ErrorCodes.PASSWORD_MISMATCH, "Invalid password");
    }

    @Override
    public UserPayload resetPassword(ResetPasswordPayload payload) {
        if (payload == null || Utils.nullOrEmpty(payload.getUsernameOrEmail()) || payload.getBirthDate() == null) {
            throw new BadRequestException(ErrorCodes.INVALID_PAYLOAD, "Invalid payload", "Invalid payload");
        }

        var mayBeUser = repository.findByUsernameOrEmail(payload.getUsernameOrEmail());
        if (mayBeUser.isEmpty()) {
            throw new BadRequestException(ErrorCodes.USER_NOT_FOUND, ErrorMessages.THE_USER_IS_NOT_FOUND.formatted(payload.getUsernameOrEmail()));
        }
        var user = mayBeUser.get();
        if (user.getBirthday().isEqual(payload.getBirthDate())) {
            log.info("[UserService.resetPassword] found matched user {}.", Utils.toJson(user));
            return mapper.toPayload(user);
        }

        throw new BadRequestException(ErrorCodes.USER_NOT_FOUND, ErrorMessages.THE_USER_IS_NOT_FOUND.formatted(payload.getUsernameOrEmail()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Utils.nullOrBlank(username)) {
            throw new UsernameNotFoundException("Username or email cannot be null or blank");
        }
        var mayBeUser = repository.findByUsernameOrEmail(username);
        if (mayBeUser.isEmpty()) {
            throw new UsernameNotFoundException(ErrorMessages.THE_USER_IS_NOT_FOUND.formatted(username));
        }
        var user = mayBeUser.get();
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("ADMIN")
            .build();
    }
}
