package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.AccountUpdateRequest;
import iuh.fit.se.techgalaxy.dto.response.AccountUpdateResponse;
import iuh.fit.se.techgalaxy.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
     AccountUpdateResponse toAccountResponse(Account account);
     Account toAccount(AccountUpdateRequest accountRequest);

}
