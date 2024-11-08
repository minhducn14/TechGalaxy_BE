package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping
    public ResponseEntity<DataResponse<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .data(customerServiceImpl.findAll())
                .build());
    }

    @PostMapping
    public ResponseEntity<DataResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customerResponses.add(customerServiceImpl.save(request));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .data(customerResponses)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CustomerResponse>> getCustomerById(@PathVariable String id) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customerResponses.add(customerServiceImpl.findById(id));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .data(customerResponses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CustomerResponse>> updateCustomer(@PathVariable String id, @RequestBody CustomerRequest request) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customerResponses.add(customerServiceImpl.update(id, request));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .data(customerResponses)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Object>> deleteCustomer(@PathVariable String id) {
        customerServiceImpl.delete(id);
        return ResponseEntity.ok(DataResponse.<Object>builder().message("Delete " + id + " success").build());
    }

}
