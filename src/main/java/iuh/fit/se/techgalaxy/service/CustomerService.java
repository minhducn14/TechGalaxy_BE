package iuh.fit.se.techgalaxy.service;
import iuh.fit.se.techgalaxy.dto.request.CustomerRequest;
import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import org.springframework.hateoas.PagedModel;
import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findByEmail(String email);

    PagedModel<CustomerResponse> findAllCustomers(int page, int size);

    CustomerResponse findById(String id);

    CustomerResponse save(CustomerRequest customerRequest);

    CustomerResponse update(CustomerRequest customerRequest);

    boolean delete(String id);
}
