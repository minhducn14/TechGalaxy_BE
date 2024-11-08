package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.entities.Account;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    /**
     * Tạo mới một tài khoản.
     * @param account đối tượng Account cần tạo
     * @return Account đã được lưu trong cơ sở dữ liệu
     */
    Account createAccount(Account account);

    /**
     * Lấy thông tin tài khoản theo ID.
     * @param id ID của tài khoản
     * @return Optional<Account> chứa thông tin tài khoản nếu tìm thấy, hoặc rỗng nếu không tìm thấy
     * @Author: Vũ Nguyễn Minh Đức
     */
    Optional<Account> getAccountById(String id);

    /**
     * Cập nhật thông tin tài khoản.
     * @param account đối tượng Account đã chỉnh sửa
     * @return Account đã được cập nhật
     * @Author: Vũ Nguyễn Minh Đức
     */
    Account updateAccount(Account account);

    /**
     * Xóa tài khoản theo ID.
     * @param id ID của tài khoản cần xóa
     * @Author: Vũ Nguyễn Minh Đức
     */
    boolean deleteAccountById(String id);

    /**
     * Lấy danh sách tất cả tài khoản.
     * @return List<Account> danh sách tất cả tài khoản
     * @Author: Vũ Nguyễn Minh Đức
     */
    List<Account> findAllAccounts();

    /**
     * Tìm kiếm tài khoản dựa trên các tiêu chí lọc.
     * @param spec tiêu chí tìm kiếm (Specification)
     * @return List<Account> danh sách tài khoản thỏa mãn tiêu chí
     * @Author: Vũ Nguyễn Minh Đức
     */
    List<Account> findAccountsByCriteria(Specification<Account> spec);

    /**
     * Lấy thông tin tài khoản dựa trên email.
     * @param email email của tài khoản
     * @return Optional<Account> chứa thông tin tài khoản nếu tìm thấy, hoặc rỗng nếu không tìm thấy
     * @Author: Vũ Nguyễn Minh Đức
     */
    Optional<Account> getAccountByEmail(String email);

    /**
     * Đặt lại mật khẩu cho tài khoản.
     * @param id ID của tài khoản
     * @param newPassword mật khẩu mới
     * @Author: Vũ Nguyễn Minh Đức
     */
    void resetPassword(String id, String newPassword);

    /**
     * Xác thực tài khoản dựa trên email và mật khẩu.
     * @param email email của tài khoản
     * @param password mật khẩu của tài khoản
     * @return true nếu tài khoản hợp lệ, ngược lại là false
     * @Author: Vũ Nguyễn Minh Đức
     */
    boolean validateAccount(String email, String password);
}
