package iuh.fit.se.techgalaxy.entities;

import iuh.fit.se.techgalaxy.entities.enumeration.DetailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders_Details")
public class OrderDetail {

    @Id
    @UuidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DetailStatus detailStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_variant_detail_id")
    private ProductVariantDetail productVariantDetail;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
