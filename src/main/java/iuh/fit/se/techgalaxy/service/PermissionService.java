package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.Permission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface PermissionService {
    public boolean isPermissionExist(Permission p);

    public Permission fetchById(String id);

    public Permission create(Permission p);

    public Permission update(Permission p);

    public void delete(String id);

    public ResultPaginationDTO getPermissions(Specification<Permission> spec, Pageable pageable);

    public boolean isSameName(Permission p);


}
