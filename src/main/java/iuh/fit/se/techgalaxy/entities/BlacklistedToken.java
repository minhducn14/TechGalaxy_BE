package iuh.fit.se.techgalaxy.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blacklisted_tokens")
public class BlacklistedToken {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(1000)")
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}