package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrademarkRepository extends JpaRepository<Trademark, String> {
    Trademark findTrademarkByName(String name);
}
