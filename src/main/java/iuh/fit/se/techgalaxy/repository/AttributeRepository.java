package iuh.fit.se.techgalaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iuh.fit.se.techgalaxy.entities.Attribute;


@Repository
public interface AttributeRepository extends JpaRepository<Attribute, String>{

}
