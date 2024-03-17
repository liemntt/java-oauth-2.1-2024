package dev.thanhliem.oauth.mappers;

import dev.thanhliem.oauth.configurations.MapperGlobalConfig;
import dev.thanhliem.oauth.models.entities.User;
import dev.thanhliem.oauth.models.payloads.SignUpPayload;
import dev.thanhliem.oauth.models.payloads.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperGlobalConfig.class)
public interface UserMapper {

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    User toEntity(UserPayload payload);

    UserPayload toPayload(User user);

    User toUser(SignUpPayload payload);
}

