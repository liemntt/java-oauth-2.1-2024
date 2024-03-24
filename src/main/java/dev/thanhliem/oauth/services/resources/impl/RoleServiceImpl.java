package dev.thanhliem.oauth.services.resources.impl;

import dev.thanhliem.oauth.models.entities.Role;
import dev.thanhliem.oauth.repositories.mappers.RoleRepository;
import dev.thanhliem.oauth.services.resources.RoleService;
import dev.thanhliem.oauth.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Optional<Role> findByName(String name) {
        if (Utils.nullOrBlank(name)) {
            log.warn("[RoleService.findByName] Name cannot be null or blank");
            return Optional.empty();
        }
        var mayBeRole = repository.findByName(name);
        if (mayBeRole.isPresent()) {
            log.info("[RoleService.findByName] Role {}", Utils.toJson(mayBeRole.get()));
            return mayBeRole;
        }
        log.warn("[RoleService.findByName] Role {} not found", name);
        return Optional.empty();
    }

    @Override
    public List<Role> findUserRoles(Long userId) {
        if (userId == null || userId <= 0) {
            log.warn("[RoleService.findUserRoles] Invalid userId");
            return Collections.emptyList();
        }
        var roles = repository.findByUserId(userId);
        if (Utils.nonEmpty(roles)) {
            log.info("[RoleService.findUserRoles] User {} roles {}", userId, Utils.toJson(roles));
            return roles;
        }
        log.warn("[RoleService.findUserRoles] User {} does not have any roles", userId);
        return Collections.emptyList();
    }
}
