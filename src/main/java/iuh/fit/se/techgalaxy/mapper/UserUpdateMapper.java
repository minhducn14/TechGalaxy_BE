package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.UserUpdateResponse;
import iuh.fit.se.techgalaxy.entities.SystemUser;
import org.mapstruct.factory.Mappers;

public interface UserUpdateMapper {
    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    /**
     * Convert User to UserUpdateResponse
     *
     * @param user
     * @return UserUpdateResponse
     * author: Vu Nguyen Minh Duc
     */
    UserUpdateResponse convertToResUpdateUserDTO(SystemUser user);


    /**
     * Convert UserUpdateResponse to User
     *
     * @param userUpdateResponse
     * @return User
     * author: Vu Nguyen Minh Duc
     */
    SystemUser convertToUser(UserUpdateResponse userUpdateResponse);


}
