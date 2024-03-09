package dev.thanhliem.oauth.configurations;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(
    componentModel = "spring",
    suppressTimestampInGenerated = true,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public class MapperGlobalConfig {
}
