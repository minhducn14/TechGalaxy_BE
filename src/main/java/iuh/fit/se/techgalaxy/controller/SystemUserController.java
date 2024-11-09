package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.SystemUser;
import iuh.fit.se.techgalaxy.entities.enumeration.SystemUserStatus;
import iuh.fit.se.techgalaxy.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/system-users")
public class SystemUserController {
    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }
    @GetMapping
    public ResponseEntity<DataResponse<ResultPaginationDTO>> getAllSystemUsers(Specification<SystemUser> spec, Pageable pageable) {
        ResultPaginationDTO result = systemUserService.fetchAllSystemUser(spec, pageable);
        DataResponse<ResultPaginationDTO> response = DataResponse.<ResultPaginationDTO>builder()
                .status(200)
                .message("Fetch all system users successfully")
                .data(List.of(result))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<SystemUser>> getSystemUserById(@PathVariable String id) {
        SystemUser user = systemUserService.fetchUserById(id);
        DataResponse<SystemUser> response = DataResponse.<SystemUser>builder()
                .status(200)
                .message("User fetched successfully")
                .data(List.of(user))
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<DataResponse<List<SystemUser>>> getSystemUsersByStatus(@PathVariable SystemUserStatus status) {
        List<SystemUser> users = systemUserService.handleGetSystemUsersByStatus(status);
        DataResponse<List<SystemUser>> response = DataResponse.<List<SystemUser>>builder()
                .status(200)
                .message("Users with status " + status + " fetched successfully")
                .data(List.of(users))
                .build();

        return ResponseEntity.ok(response);
    }
}
