package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.RoleRequest;
import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.dto.response.RoleResponse;
import iuh.fit.se.techgalaxy.entities.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RoleService {
    /**
     * Check if role exists by name
     *
     * @param name
     * @return boolean
     * author: Vũ Nguyễn Minh Đức
     */
    boolean existsByName(String name);

    /**
     * Find role by name
     *
     * @param name
     * @return RoleResponse
     * author: Vũ Nguyễn Minh Đức
     */
    RoleResponse findByName(String name);

    /**
     * Create new role
     *
     * @param request
     * @return RoleResponse
     * author: Vũ Nguyễn Minh Đức
     */
    RoleResponse create(RoleRequest request);


    /**
     * Fetch role by id
     *
     * @param id
     * @return RoleResponse
     * author: Vũ Nguyễn Minh Đức
     */
    RoleResponse fetchById(String id);


    /**
     * Update role
     *
     * @param request
     * @return Role
     * author: Vũ Nguyễn Minh Đức
     */
    RoleResponse update(RoleRequest request);

    /**
     * Delete role
     *
     * @param id author: Vũ Nguyễn Minh Đức
     */
    public void delete(String id);

    /**
     * Get all roles
     *
     * @param spec
     * @param pageable
     * @return ResultPaginationDTO
     * author: Vũ Nguyễn Minh Đức
     */
    public ResultPaginationDTO getRoles(Specification<Role> spec, Pageable pageable);


    //findByNameIn
    /**
     * Find roles by name in list
     *
     * @param names
     * @return List<Role>
     * author: Vũ Nguyễn Minh Đức
     */
    public List<Role> findByNameIn(List<String> names);

    public List<RoleResponse> findAll();
}
