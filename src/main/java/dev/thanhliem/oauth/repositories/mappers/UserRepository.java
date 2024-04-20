package dev.thanhliem.oauth.repositories.mappers;

import dev.thanhliem.oauth.models.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAllUsers();

    int signUp(@Param(value = "user") User user);

    Optional<User> find(@Param(value = "id") long id);

    Optional<User> findByUsernameOrEmail(@Param(value = "username") String username);

    Optional<Boolean> isExistUsernameOrEmail(@Param(value = "username") String username, @Param(value = "email") String email);

    int insertRelationship(@Param("userId")Long userId, @Param("roleId") Long roleId);

    int update(@Param(value = "user") User user);
}
