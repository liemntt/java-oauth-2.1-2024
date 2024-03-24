package dev.thanhliem.oauth.apis;

import dev.thanhliem.oauth.constants.Endpoints;
import dev.thanhliem.oauth.models.entities.User;
import dev.thanhliem.oauth.models.payloads.ResetPasswordPayload;
import dev.thanhliem.oauth.models.payloads.SignInPayload;
import dev.thanhliem.oauth.models.payloads.SignUpPayload;
import dev.thanhliem.oauth.models.payloads.UserPayload;
import dev.thanhliem.oauth.models.responses.RequestTokenResponse;
import dev.thanhliem.oauth.services.resources.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApis {

    private final UserService service;

    @GetMapping(value = Endpoints.UserApi.GET_ALL_USERS,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(value = Endpoints.UserApi.GET_USER_BY_ID,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPayload> find(@PathVariable(value = "id")long id) {
        UserPayload result = service.find(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = Endpoints.UserApi.SIGN_UP,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPayload> signUp(@RequestBody SignUpPayload payload) {
        return ResponseEntity.ok(service.signUp(payload));
    }

    @PostMapping(value = Endpoints.UserApi.SIGNING,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestTokenResponse> signIn(@RequestBody SignInPayload payload) {
        return ResponseEntity.ok(service.signIn(payload));
    }

    @PostMapping(value = Endpoints.UserApi.RESET_PASSWORD,
    headers = {Endpoints.HEADER_VERSION},
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPayload> resetPassword(@RequestBody ResetPasswordPayload payload) {
        return ResponseEntity.ok(service.resetPassword(payload));
    }
}
