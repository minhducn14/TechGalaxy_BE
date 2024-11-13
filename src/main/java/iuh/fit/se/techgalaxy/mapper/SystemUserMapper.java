package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.SystemUserRequest;
import iuh.fit.se.techgalaxy.dto.response.SystemUserResponse;
import iuh.fit.se.techgalaxy.entities.SystemUser;
import org.mapstruct.factory.Mappers;

public interface SystemUserMapper {
    SystemUserMapper INSTANCE = Mappers.getMapper(SystemUserMapper.class);

    SystemUser toSystemUserFromResponse(SystemUserResponse systemUserResponse);

    SystemUserResponse toSystemUserResponse(SystemUser systemUser);

    SystemUser toSystemUserFromRequest(SystemUserRequest systemUserRequest);

    SystemUserRequest toSystemUserRequest(SystemUser systemUser);
}
