package iuh.fit.se.techgalaxy.entities;

import iuh.fit.se.techgalaxy.entities.constant.SystemUserLevel;
import iuh.fit.se.techgalaxy.entities.constant.SystemUserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "System_Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser {

    @Id
    @UuidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemUserStatus systemUserStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemUserLevel level;

    @ManyToOne
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private String gender;

    @Column(length = 255)
    private String avatar;

    @OneToMany(mappedBy = "systemUser", cascade = CascadeType.ALL)
    private List<Order> order;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
