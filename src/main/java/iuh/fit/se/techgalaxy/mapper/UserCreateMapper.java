package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.UserCreateResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.User;

public interface UserCreateMapper {
    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    /**
     * Convert User to UserCreateResponse
     *
     * @param user
     * @return UserCreateResponse
     * author: Vu Nguyen Minh Duc
     */
    UserCreateResponse convertToResCreateUserDTO(User user);


    /**
     * Convert UserCreateResponse to User
     *
     * @param userCreateResponse
     * @return User
     * author: Vu Nguyen Minh Duc
     */
    User convertToUser(UserCreateResponse userCreateResponse);
}
