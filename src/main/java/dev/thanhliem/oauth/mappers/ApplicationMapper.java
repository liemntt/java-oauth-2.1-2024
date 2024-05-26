package dev.thanhliem.oauth.mappers;

import dev.thanhliem.oauth.configurations.MapperGlobalConfig;
import dev.thanhliem.oauth.models.entities.Application;
import dev.thanhliem.oauth.models.payloads.ApplicationPayLoad;
import dev.thanhliem.oauth.models.responses.ApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperGlobalConfig.class)
public interface ApplicationMapper {

    ApplicationResponse toResp(Application application);

    default List<ApplicationResponse> toRespList(List<Application> applications) {
        return applications.parallelStream()
            .map(this::toResp)
            .toList();
    }
    @Mapping(target = "updatedDate", expression = "java(java.time.OffsetDateTime.now())")
    Application toEntity(ApplicationPayLoad payLoad);
}
