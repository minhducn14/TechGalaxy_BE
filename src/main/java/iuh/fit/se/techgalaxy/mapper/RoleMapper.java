package iuh.fit.se.techgalaxy.mapper;


import iuh.fit.se.techgalaxy.dto.request.RoleRequest;
import iuh.fit.se.techgalaxy.dto.response.RoleResponse;
import iuh.fit.se.techgalaxy.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    /**
     * Convert Request to Entity
     * @param request
     * @return Role
     * author: Vu Nguyen Minh Duc
     */
    Role toEntity(RoleRequest request);

    /**
     * Convert Response to Entity
     * @param response
     * @return Role
     * author: Vu Nguyen Minh Duc
     */
    Role toEntity(RoleResponse response);


    /**
     * Convert Entity to Response
     * @param role
     * @return RoleResponse
     * author: Vu Nguyen Minh Duc
     */
    RoleResponse toResponse(Role role);

    /**
     * Convert Entity to Request
     * @param role
     * @return RoleRequest
     * author: Vu Nguyen Minh Duc
     */
    RoleRequest toRequest(Role role);


}