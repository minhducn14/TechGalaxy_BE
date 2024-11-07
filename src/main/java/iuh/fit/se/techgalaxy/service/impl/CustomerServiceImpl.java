package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.entities.Customer;
import iuh.fit.se.techgalaxy.mapper.CustomerMapper;
import iuh.fit.se.techgalaxy.repository.CustomerRepository;
import iuh.fit.se.techgalaxy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PagedModel<CustomerResponse> findAllCustomers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findAll(pageRequest);

        List<CustomerResponse> customerResponses = customerPage.getContent()
                .stream()
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .collect(Collectors.toList());
        return PagedModel.of(
                customerResponses,
                new PagedModel.PageMetadata(
                        customerPage.getSize(),
                        customerPage.getNumber(),
                        customerPage.getTotalElements()
                )
        );
    }

    @Override
    public List<CustomerResponse> findByEmail(String email) {
        return null;
    }

}
