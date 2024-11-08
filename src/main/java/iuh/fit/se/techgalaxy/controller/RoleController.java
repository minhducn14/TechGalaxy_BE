package iuh.fit.se.techgalaxy.controller;

import com.turkraft.springfilter.boot.Filter;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.Permission;
import iuh.fit.se.techgalaxy.entities.Role;
import iuh.fit.se.techgalaxy.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping("/roles")
    public ResponseEntity<DataResponse<Role>> create(@Valid @RequestBody Role r) {
        // check name
        if (this.roleService.existsByName(r.getName())) {
            return ResponseEntity.badRequest().body(DataResponse.<Role>builder().message("Role đã tồn tại.").build());
        }
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleService.create(r));
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.<Role>builder().data(roles).build());
    }

    @PutMapping("/roles")
    public ResponseEntity<DataResponse<Role>> update(@Valid @RequestBody Role r) {
        // check id
        if (this.roleService.fetchById(r.getId()) == null) {
            return ResponseEntity.badRequest().body(DataResponse.<Role>builder().message("Role đã tồn tại.").build());
        }

        List<Role> roles = new ArrayList<>();
        roles.add(this.roleService.update(r));
        return ResponseEntity.ok().body(DataResponse.<Role>builder().data(roles).build());
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<DataResponse<Void>> delete(@PathVariable("id") String id) {
        // check id
        if (this.roleService.fetchById(id) == null) {
            return ResponseEntity.badRequest().body(DataResponse.<Void>builder().message("Role với id = " + id + " không tồn tại.").build());
        }
        this.roleService.delete(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/roles")
    public ResponseEntity<DataResponse<ResultPaginationDTO>> getPermissions(
            @Filter Specification<Role> spec, Pageable pageable) {

        List<ResultPaginationDTO> roles = new ArrayList<>();
        roles.add(this.roleService.getRoles(spec, pageable));

        return ResponseEntity.ok().body(DataResponse.<ResultPaginationDTO>builder().data(roles).build());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<DataResponse<Role>> getById(@PathVariable("id") String id) {

        Role role = this.roleService.fetchById(id);
        if (role == null) {
            return ResponseEntity.badRequest().body(DataResponse.<Role>builder().message("Role với id = " + id + " không tồn tại.").build());
        }

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return ResponseEntity.ok().body(DataResponse.<Role>builder().data(roles).build());
    }
}
