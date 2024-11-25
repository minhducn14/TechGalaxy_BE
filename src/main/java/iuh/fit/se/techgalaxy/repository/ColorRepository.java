package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "colors", path = "colors", exported = false)
public interface ColorRepository extends JpaRepository<Color, String> {
    List<Color> findColorsByIdIsIn(List<String> ids);

}
