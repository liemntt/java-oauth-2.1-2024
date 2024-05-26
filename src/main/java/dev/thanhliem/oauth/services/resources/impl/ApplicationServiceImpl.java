package dev.thanhliem.oauth.services.resources.impl;

import dev.thanhliem.oauth.constants.ErrorCodes;
import dev.thanhliem.oauth.constants.ErrorMessages;
import dev.thanhliem.oauth.exceptions.BadRequestException;
import dev.thanhliem.oauth.exceptions.InternalException;
import dev.thanhliem.oauth.mappers.ApplicationMapper;
import dev.thanhliem.oauth.models.payloads.ApplicationPayLoad;
import dev.thanhliem.oauth.models.responses.ApplicationResponse;
import dev.thanhliem.oauth.repositories.mappers.ApplicationRepository;
import dev.thanhliem.oauth.services.resources.ApplicationService;
import dev.thanhliem.oauth.services.resources.UserService;
import dev.thanhliem.oauth.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    private final ApplicationMapper mapper;

    private final ObjectProvider<UserService> userServiceObjectProvider;

    @Override
    public List<ApplicationResponse> getAllByUserId(Long userId) {
        var mayBeUserId = Optional.ofNullable(userId).filter(id -> id > 0);
        if (mayBeUserId.isEmpty()) {
            log.info("[getAllByUserId] Invalid user id {}", userId);
            throw new BadRequestException(ErrorCodes.INVALID_PAYLOAD, ErrorMessages.INVALID_PAYLOAD, "Invalid userId %s".formatted(userId));
        }
        var applications = repository.getByUserId(userId);
        log.info("[getAllByUserId][{}] get applications {}", userId, Utils.toJson(applications));
        return mapper.toRespList(applications);
    }

    @Override
    public ApplicationResponse getById(Long id, Long userId) {
        var mayBeId = Optional.ofNullable(id).filter(value -> value > 0);
        if (mayBeId.isEmpty()) {
            log.info("[getById] Invalid application id {}", id);
            throw new BadRequestException(ErrorCodes.INVALID_PAYLOAD, ErrorMessages.INVALID_PAYLOAD, "Invalid application %s".formatted(userId));
        }
        var mayBeUserId = Optional.ofNullable(userId).filter(value -> value > 0);
        if (mayBeUserId.isEmpty()) {
            log.info("[getById] Invalid user id {}", userId);
            throw new BadRequestException(ErrorCodes.INVALID_PAYLOAD, ErrorMessages.INVALID_PAYLOAD, "Invalid userId %s".formatted(userId));
        }
        var application = repository.getById(id, userId);
        if (application.isEmpty()) {
            throw new BadRequestException(ErrorCodes.APPLICATION_NOT_FOUND, ErrorMessages.APPLICATION_NOT_FOUND,
                "UserId %s application %s not found".formatted(userId, id));
        }
        log.info("[getById][{}][{}] Application {}", userId, id, Utils.toJson(application));
        return mapper.toResp(application.get());
    }

    @Override
    public ApplicationResponse update(Long id, Long userId) {
        return null;
    }

    @Override
    public ApplicationResponse create(ApplicationPayLoad payLoad, Long userId) {
        if (payLoad == null) {
            throw new BadRequestException(ErrorCodes.INVALID_PAYLOAD, ErrorMessages.INVALID_PAYLOAD, "Payload cannot be null/empty");
        }
        var mayBeUserId = Optional.ofNullable(userId).filter(id -> id > 0);
        if (mayBeUserId.isEmpty()) {
            throw new BadRequestException(ErrorCodes.INVALID_PARAMS, ErrorMessages.INVALID_PAYLOAD, "Param userId is invalid");
        }
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
            var username = usernamePasswordAuthenticationToken.getName();
            var mayBeUser = userServiceObjectProvider.getObject().findByUsername(username)
                .filter(u -> !Objects.equals(u.getId(), userId));
            if (mayBeUser.isEmpty()) {
                throw new BadRequestException(ErrorCodes.BAD_CREDENTIALS, ErrorMessages.THE_USER_IS_NOT_FOUND.formatted(username));
            }
            payLoad.setUserId(userId);
            var application = mapper.toEntity(payLoad);
            var numberOfRecord = repository.create(application);
            if (numberOfRecord == 0) {
                throw new InternalException(ErrorCodes.CANNOT_CREATE_APPLICATION, ErrorMessages.CANNOT_CREATE_APPLICATION);
            }
            return mapper.toResp(application);
        }
        throw new BadRequestException(ErrorCodes.UNAUTHORIZED, ErrorMessages.INVALID_AUTHENTICATION,
            "Cannot get username from authentication");
    }

    @Override
    public ApplicationResponse delete(Long id, Long userId) {
        return null;
    }
}
