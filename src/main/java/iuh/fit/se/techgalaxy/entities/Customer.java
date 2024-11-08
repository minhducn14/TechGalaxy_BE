package iuh.fit.se.techgalaxy.entities;

import iuh.fit.se.techgalaxy.entities.enumeration.CustomerStatus;
import iuh.fit.se.techgalaxy.entities.enumeration.Gender;
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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @UuidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 50, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 255)
    private String avatar;

    @Column
    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
