package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.SystemUserRequestDTO;
import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.dto.response.SystemUserResponseDTO;
import iuh.fit.se.techgalaxy.entities.SystemUser;
import iuh.fit.se.techgalaxy.entities.enumeration.SystemUserStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;

import java.util.List;


public interface SystemUserService {

    /**
     * Handle create user
     *
     * @param user
     * @return User
     * author: Vũ Nguyễn Minh Đức
     */
    public SystemUserResponseDTO handleCreateSystemUser(SystemUserRequestDTO user);

    /**
     * Handle delete user
     *
     * @param id author: Vũ Nguyễn Minh Đức
     */
    public void handleDeleteSystemUser(String id);


    /**
     * Fetch user by id
     *
     * @param id
     * @return User
     * author: Vũ Nguyễn Minh Đức
     */
    public SystemUserResponseDTO fetchUserById(String id);

    public List<SystemUserResponseDTO> fetchAllSystemUser();

    /**
     * Fetch all user
     *
     * @param spec
     * @param pageable
     * @return ResultPaginationDTO
     * author: Vũ Nguyễn Minh Đức
     */
    public ResultPaginationDTO fetchAllSystemUser(Specification<SystemUser> spec, Pageable pageable);

    /**
     * Handle update user
     *
     * @param reqUser
     * @return User
     * author: Vũ Nguyễn Minh Đức
     */
    public SystemUserResponseDTO handleUpdateSystemUser(SystemUserRequestDTO reqUser);

    /**
     * Check if email exist
     *
     * @param email
     * @return boolean
     * author: Vũ Nguyễn Minh Đức
     */
    public boolean isEmailExist(String email);

    /**
     * Handle get user by status
     *
     * @param status
     * @return List<SystemUser>
     * author: Vũ Nguyễn Minh Đức
     */
    public List<SystemUserResponseDTO> handleGetSystemUsersByStatus(SystemUserStatus status);

}
