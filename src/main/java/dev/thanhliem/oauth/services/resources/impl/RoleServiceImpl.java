package dev.thanhliem.oauth.services.resources.impl;

import dev.thanhliem.oauth.repositories.mappers.RoleRepository;
import dev.thanhliem.oauth.services.resources.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
}
