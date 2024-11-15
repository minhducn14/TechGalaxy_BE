package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Permission;
import iuh.fit.se.techgalaxy.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "roles", path = "roles", exported = false)

public interface RoleRepository extends JpaRepository<Role, String>,
        JpaSpecificationExecutor<Role> {
    boolean existsByName(String name);

    Role findByName(String name);

    List<Role> findByIdIn(List<String> id);


    List<Role> findByNameIn(List<String> name);
}
