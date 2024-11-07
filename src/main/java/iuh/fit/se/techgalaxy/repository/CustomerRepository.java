package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM  Customer c JOIN c.account a WHERE a.email LIKE :email")
    public List<Customer> findByEmail(String email);

    public PagedModel<Customer> findAllCustomers(int page, int size);
}
