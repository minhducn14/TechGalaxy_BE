package iuh.fit.se.techgalaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iuh.fit.se.techgalaxy.entities.Value;

@Repository
public interface ValueRepository extends JpaRepository<Value, String>{

}
