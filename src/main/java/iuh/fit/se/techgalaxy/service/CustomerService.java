package iuh.fit.se.techgalaxy.service;
import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import org.springframework.hateoas.PagedModel;
import java.util.List;

public interface CustomerService {
    public List<CustomerResponse> findByEmail(String email);

    public PagedModel<CustomerResponse> findAllCustomers(int page, int size);
}
