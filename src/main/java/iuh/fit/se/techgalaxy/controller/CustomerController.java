package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<DataResponse<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .message("Get all customers success")
                .data(customerService.findAll())
                .build());
    }

    @PostMapping
    public ResponseEntity<DataResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customerResponses.add(customerService.save(request));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .message("Create customer success")
                .data(customerResponses)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CustomerResponse>> getCustomerById(@PathVariable String id) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customerResponses.add(customerService.findById(id));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .message("Get customer by id success")
                .data(customerResponses)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CustomerResponse>> updateCustomer(@PathVariable String id, @RequestBody CustomerRequest request) {
        List<CustomerResponse> customerResponses = List.of(customerService.update(id, request));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .message("Update customer success")
                .data(customerResponses)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Object>> deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
        return ResponseEntity.ok(DataResponse.<Object>builder()
                .message("Delete " + id + " success")
                .build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DataResponse<CustomerResponse>> getCustomerByEmail(@PathVariable String email) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customerResponses.add(customerService.findByEmail(email));
        return ResponseEntity.ok(DataResponse.<CustomerResponse>builder()
                .message("Get customer by email success")
                .data(customerResponses)
                .build());
    }
}
