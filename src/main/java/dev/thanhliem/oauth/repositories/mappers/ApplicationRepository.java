package dev.thanhliem.oauth.repositories.mappers;

import dev.thanhliem.oauth.models.entities.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {

    List<Application> getByUserId(@Param("userId") Long userId);

    Optional<Application> getById(@Param("id") Long id, @Param("userId") Long userId);

    int create(@Param("application") Application application);

    int update(@Param("application") Application application);

    Boolean delete(@Param("application") Application application);
}
