package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.entities.Customer;
import iuh.fit.se.techgalaxy.mapper.CustomerMapper;
import iuh.fit.se.techgalaxy.repository.CustomerRepository;
import iuh.fit.se.techgalaxy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PagedModel<CustomerResponse> findAllCustomers(int page, int size) {
        return null;
    }

    @Override
    public List<CustomerResponse> findByEmail(String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        return customers.stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

}
