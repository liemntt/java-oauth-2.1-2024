package dev.thanhliem.oauth.apis;

import dev.thanhliem.oauth.constants.Endpoints;
import dev.thanhliem.oauth.models.payloads.ApplicationPayLoad;
import dev.thanhliem.oauth.models.responses.ApplicationResponse;
import dev.thanhliem.oauth.services.resources.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationApis {

    private final ApplicationService service;

    @PostMapping(value = Endpoints.ApplicationApi.CREATE_APP,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationResponse> create(@RequestBody ApplicationPayLoad payLoad, @PathVariable Long userId) {
        return ResponseEntity.ok(service.create(payLoad, userId));
    }
}
