package dev.thanhliem.oauth.services.resources;

import dev.thanhliem.oauth.models.payloads.ApplicationPayLoad;
import dev.thanhliem.oauth.models.responses.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> getAllByUserId(Long userId);

    ApplicationResponse getById(Long id, Long userId);

    ApplicationResponse update(Long id, Long userId);

    ApplicationResponse create(ApplicationPayLoad payLoad, Long userId);

    ApplicationResponse delete(Long id, Long userId);
}
