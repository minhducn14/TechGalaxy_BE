package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Customer;

public interface CustomerRepository {
    public Customer save(Customer customer);

    public Customer findById(String id);

    public Customer findByEmail(String email);

    public Customer findByPhone(String phone);

//    public

    public Customer update(Customer customer);

    public Customer delete(Customer customer);
}
