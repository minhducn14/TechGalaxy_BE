package iuh.fit.se.techgalaxy.controller;

import com.turkraft.springfilter.boot.Filter;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.Permission;
import iuh.fit.se.techgalaxy.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @PostMapping("/permissions")
    public ResponseEntity<DataResponse<Permission>> create(@Valid @RequestBody Permission p) {
        // check exist
        if (this.permissionService.isPermissionExist(p)) {
            return ResponseEntity.badRequest().body(DataResponse.<Permission>builder().message("Permission đã tồn tại.").build());
        }

        List<Permission> permissions = new ArrayList<>();
        permissions.add(this.permissionService.create(p));
        // create new permission
        return ResponseEntity.ok().body(DataResponse.<Permission>builder().data(permissions).build());
    }

    @PutMapping("/permissions")
    public ResponseEntity<DataResponse<Permission>> update(@Valid @RequestBody Permission p) {
        // check exist by id
        if (this.permissionService.fetchById(p.getId()) == null) {
            return ResponseEntity.badRequest().body(DataResponse.<Permission>builder().message("Permission với id = " + p.getId() + " không tồn tại.").build());
        }

        // check exist by module, apiPath and method
        if (this.permissionService.isPermissionExist(p)) {
            // check name
            if (this.permissionService.isSameName(p)) {
                return ResponseEntity.badRequest().body(DataResponse.<Permission>builder().message("Permission đã tồn tại.").build());
            }
        }

        List<Permission> permissions = new ArrayList<>();
        permissions.add(this.permissionService.update(p));
        // update permission
        return ResponseEntity.ok().body(DataResponse.<Permission>builder().data(permissions).build());
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<DataResponse<Void>> delete(@PathVariable("id") String id)  {
        // check exist by id
        if (this.permissionService.fetchById(id) == null) {
            return ResponseEntity.badRequest().body(DataResponse.<Void>builder().message("Permission với id = " + id + " không tồn tại.").build());
        }
        this.permissionService.delete(id);
        return ResponseEntity.ok().body(DataResponse.<Void>builder().message("Xóa permission thành công.").build());
    }

    @GetMapping("/permissions")
    public ResponseEntity<DataResponse<ResultPaginationDTO>> getPermissions(
            @Filter Specification<Permission> spec, Pageable pageable) {

        List<ResultPaginationDTO> resultPaginationDTOS = new ArrayList<>();
        resultPaginationDTOS.add(this.permissionService.getPermissions(spec, pageable));

        return ResponseEntity.ok().body(DataResponse.<ResultPaginationDTO>builder().data(resultPaginationDTOS).build());
    }
}