package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>,
        JpaSpecificationExecutor<Role> {
    boolean existsByName(String name);

    Role findByName(String name);

    List<Role> findByNameIn(List<String> name);
}
