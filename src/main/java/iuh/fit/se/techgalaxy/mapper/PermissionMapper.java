package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.PermissionRequest;
import iuh.fit.se.techgalaxy.dto.response.PermissionResponse;
import iuh.fit.se.techgalaxy.entities.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    @Mapping(target = "id", ignore = true) // Bỏ qua trường id khi tạo mới
    Permission toEntity(PermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    @Mapping(target = "id", ignore = true) // Không cập nhật id khi sửa
    void updateEntityFromRequest(PermissionRequest request, @MappingTarget Permission permission);
}
