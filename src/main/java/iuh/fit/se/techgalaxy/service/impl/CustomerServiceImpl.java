package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.Customer;
import iuh.fit.se.techgalaxy.mapper.CustomerMapper;
import iuh.fit.se.techgalaxy.mapper.CustomerMapperImpl;
import iuh.fit.se.techgalaxy.repository.AccountRepository;
import iuh.fit.se.techgalaxy.repository.CustomerRepository;
import iuh.fit.se.techgalaxy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CustomerMapperImpl customerMapperImpl;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, CustomerMapperImpl customerMapperImpl) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.customerMapperImpl = customerMapperImpl;
    }
    /**
     * Find all customers with pagination
     * @param page
     * @param size
     * @return PagedModel<CustomerResponse>
     * author: PhamVanThanh
     */
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

    /**
     * Find all customers
     * @return List<CustomerResponse>
     * author: PhamVanThanh
     */
    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .collect(Collectors.toList());
    }

    /**
     * Find customer by id and convert to CustomerResponse if exist else return null
     * @param id
     * @return CustomerResponse
     * author: PhamVanThanh
     */
    @Override
    public CustomerResponse findById(String id) {
        return customerRepository.findById(id)
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .orElse(null);
    }

    /**
     * Save new customer
     * @param customerRequest
     * @return CustomerResponse
     * author: PhamVanThanh
     */
    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        System.out.println(customerRequest.getAccount().getId());
        Account account = accountRepository.findById(customerRequest.getAccount().getId()).orElse(null);
        Customer customer = CustomerMapper.INSTANCE.toCustomerFromRequest(customerRequest);
        customer.setAccount(account);
        Customer customerAdd = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.toCustomerResponse(customerAdd);
    }

    /**
     * Update customer if exist else return null
     * @param customerRequest
     * @return CustomerResponse
     * author: PhamVanThanh
     */
    @Override
    public CustomerResponse update(String id, CustomerRequest customerRequest) {
        if (!customerRepository.existsById(id))
            return null;
        Customer customer = customerRepository.save(CustomerMapper.INSTANCE.toCustomerFromRequest(customerRequest));
        return CustomerMapper.INSTANCE.toCustomerResponse(customer);
    }

    /**
     * Delete customer by id
     * @param id
     * @return boolean
     * author: PhamVanThanh
     */
    @Override
    public boolean delete(String id) {
        if (!customerRepository.existsById(id))
            return false;
        customerRepository.deleteById(id);
        return true;
    }

    /**
     * Find customer by email
     * @param email
     * @return List<CustomerResponse>
     * author: PhamVanThanh
     */
    @Override
    public CustomerResponse findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .stream()
                .map(CustomerMapper.INSTANCE::toCustomerResponse)
                .findFirst()
                .orElse(null);
    }
}
