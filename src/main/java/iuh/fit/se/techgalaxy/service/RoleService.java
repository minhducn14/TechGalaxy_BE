package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface RoleService {
    boolean existsByName(String name);

    Role findByName(String name);

    public Role create(Role r);

    public Role fetchById(String id);

    public Role update(Role r);

    public void delete(String id);

    public ResultPaginationDTO getRoles(Specification<Role> spec, Pageable pageable);
}
