package dev.thanhliem.oauth.apis;

import dev.thanhliem.oauth.constants.Endpoints;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InfoApis {

    @GetMapping(value = Endpoints.InfoApi.PATH,
        headers = {Endpoints.HEADER_VERSION},
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Ok");
    }
}
