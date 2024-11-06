package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static CustomerResponse toCustomerResponse(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getUserStatus(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getGender(),
                customer.getAvatar(),
                customer.getDateOfBirth()
        );
    }
}
