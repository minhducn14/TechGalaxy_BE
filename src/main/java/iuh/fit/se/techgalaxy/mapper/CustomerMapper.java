package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toCustomerResponse(Customer customer);

    CustomerRequest toCustomerRequest(Customer customer);

    Customer toCustomerFromResponse(CustomerResponse customerResponse);

    Customer toCustomerFromRequest(CustomerRequest customerRequest);
}
