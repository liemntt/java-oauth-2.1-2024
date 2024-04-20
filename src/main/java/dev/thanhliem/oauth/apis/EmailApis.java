package dev.thanhliem.oauth.apis;

import dev.thanhliem.oauth.constants.Endpoints;
import dev.thanhliem.oauth.services.functions.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailApis {

    private final EmailService service;

    @GetMapping(value = Endpoints.EmailApi.SEND,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendMail() {
        return ResponseEntity.ok(service.send());
    }
}
