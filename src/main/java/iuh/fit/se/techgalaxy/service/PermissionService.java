package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.Permission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface PermissionService {
    /**
     * Check if permission exists
     *
     * @param p
     * @return boolean
     * author: Vũ Nguyễn Minh Đức
     */
    public boolean isPermissionExist(Permission p);

    /**
     * Fetch permission by id
     *
     * @param id
     * @return Permission
     * author: Vũ Nguyễn Minh Đức
     */
    public Permission fetchById(String id);

    /**
     * Create new permission
     *
     * @param p
     * @return Permission
     * author: Vũ Nguyễn Minh Đức
     */
    public Permission create(Permission p);

    /**
     * Update permission
     *
     * @param p
     * @return Permission
     * author: Vũ Nguyễn Minh Đức
     */
    public Permission update(Permission p);

    /**
     * Delete permission
     *
     * @param id author: Vũ Nguyễn Minh Đức
     */
    public void delete(String id);

    /**
     * Get all permissions
     *
     * @param spec
     * @param pageable
     * @return ResultPaginationDTO
     * author: Vũ Nguyễn Minh Đức
     */
    public ResultPaginationDTO getPermissions(Specification<Permission> spec, Pageable pageable);

    /**
     * Check if permission has same name
     *
     * @param p
     * @return boolean
     * author: Vũ Nguyễn Minh Đức
     */
    public boolean isSameName(Permission p);


}
