package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
     * @return Role
     * author: Vũ Nguyễn Minh Đức
     */
    Role findByName(String name);

    /**
     * Create new role
     *
     * @param r
     * @return Role
     * author: Vũ Nguyễn Minh Đức
     */
    public Role create(Role r);


    /**
     * Fetch role by id
     *
     * @param id
     * @return Role
     * author: Vũ Nguyễn Minh Đức
     */
    public Role fetchById(String id);

    /**
     * Update role
     *
     * @param r
     * @return Role
     * author: Vũ Nguyễn Minh Đức
     */
    public Role update(Role r);

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
}
