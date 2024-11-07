package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    /**
     * Convert Customer to CustomerResponse
     * @param customer
     * @return CustomerResponse
     * author: PhamVanThanh
     */
    CustomerResponse toCustomerResponse(Customer customer);

    /**
     * Convert CustomerResponse to Customer
     * @param customerResponse
     * @return Customer
     * author: PhamVanThanh
     */
    Customer toCustomer(CustomerResponse customerResponse);
}
