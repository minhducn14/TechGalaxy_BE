package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "trademarks", path = "trademarks", exported = false)
public interface TrademarkRepository extends JpaRepository<Trademark, String> {
    Trademark findTrademarkByName(String name);
}
