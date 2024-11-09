package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.ResultPaginationDTO;
import iuh.fit.se.techgalaxy.entities.SystemUser;
import iuh.fit.se.techgalaxy.entities.enumeration.SystemUserStatus;
import iuh.fit.se.techgalaxy.repository.AccountRepository;
import iuh.fit.se.techgalaxy.repository.SystemUserRepository;
import iuh.fit.se.techgalaxy.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {
    private final SystemUserRepository systemUserRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public SystemUserServiceImpl(SystemUserRepository systemUserRepository, AccountRepository accountRepository) {
        this.systemUserRepository = systemUserRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public SystemUser handleCreateSystemUser(SystemUser user) {
        return systemUserRepository.save(user);
    }

    @Override
    public void handleDeleteSystemUser(String id) {
        Optional<SystemUser> userOpt = systemUserRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy người dùng với id: " + id);
        }
        systemUserRepository.deleteById(id);
    }

    @Override
    public SystemUser fetchUserById(String id) {
        return systemUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với id: " + id));
    }

    @Override
    public ResultPaginationDTO fetchAllSystemUser(Specification<SystemUser> spec, Pageable pageable) {
        Page<SystemUser> page = systemUserRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(page.getTotalPages());
        mt.setTotal(page.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(page.getContent());
        return rs;

    }

    @Override
    public SystemUser handleUpdateSystemUser(SystemUser reqUser) {
        SystemUser existingUser = fetchUserById(reqUser.getId());
        existingUser.setName(reqUser.getName());
        existingUser.setAddress(reqUser.getAddress());
        existingUser.setPhone(reqUser.getPhone());
        existingUser.setSystemUserStatus(reqUser.getSystemUserStatus());
        existingUser.setLevel(reqUser.getLevel());
        existingUser.setAvatar(reqUser.getAvatar());
        return systemUserRepository.save(existingUser);
    }

    @Override
    public SystemUser handleGetSystemUserByUsername(String username) {
        return systemUserRepository.findSystemUserByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với username: " + username));
    }

    @Override
    public SystemUser handleGetUserByEmail(String email) {
        return systemUserRepository.findSystemUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với email: " + email));
    }

    @Override
    public boolean isEmailExist(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public List<SystemUser> handleGetSystemUsersByStatus(SystemUserStatus status) {
        return systemUserRepository.findBySystemUserStatus(status);
    }
}
