package dev.thanhliem.oauth.services.resources;

import dev.thanhliem.oauth.models.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    /**
     * Find role by name
     * @param name - role name
     */
    Optional<Role> findByName(String name);

    List<Role> findUserRoles(Long userId);
}
