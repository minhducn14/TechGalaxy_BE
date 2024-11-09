package iuh.fit.se.techgalaxy.repository;

import iuh.fit.se.techgalaxy.entities.Account;
import iuh.fit.se.techgalaxy.entities.Role;
import iuh.fit.se.techgalaxy.entities.SystemUser;
import iuh.fit.se.techgalaxy.entities.enumeration.SystemUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, String>, JpaSpecificationExecutor<SystemUser> {
    // Tìm danh sách SystemUser theo trạng thái
    List<SystemUser> findBySystemUserStatus(SystemUserStatus status);

    // Tìm SystemUser theo email thông qua Account
    @Query("SELECT su FROM SystemUser su WHERE su.account.email = :email")
    Optional<SystemUser> findSystemUserByEmail(@Param("email") String email);

    // Tìm tất cả SystemUser liên kết với một Account cụ thể
    List<SystemUser> findByAccount(Account account);
}
