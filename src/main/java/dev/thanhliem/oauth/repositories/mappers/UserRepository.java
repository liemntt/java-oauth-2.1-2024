package dev.thanhliem.oauth.repositories.mappers;

import dev.thanhliem.oauth.entities.models.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAllUsers();

    int signUp(@Param(value = "user") User user);

    User find(@Param(value = "id") long id);

    User findByUsernameOrEmail(@Param(value = "username") String username);

    Optional<Boolean> isExistUsernameOrEmail(@Param(value = "username") String username, @Param(value = "email") String email);

    int insertRelationship(@Param("userId")Long userId, @Param("roleId") Long roleId);
}
