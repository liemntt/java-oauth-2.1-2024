package dev.thanhliem.oauth.repositories.mappers;

import dev.thanhliem.oauth.entities.models.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    List<Role> findByUserId(@Param(value = "userId") long userId);

    Optional<Role> findByName(@Param(value = "name") String name);

}

