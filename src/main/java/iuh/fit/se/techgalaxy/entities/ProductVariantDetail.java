package iuh.fit.se.techgalaxy.entities;

import iuh.fit.se.techgalaxy.entities.enumeration.ProductStatus;
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
@AllArgsConstructor@Entity
@Table(name = "Product_Variant_Details", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "color_id"})
})
public class ProductVariantDetail {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @Column
    private String name;

    @Column
    private Integer viewsCount;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @Column
    private Double sale;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "productVariantDetail", cascade = CascadeType.ALL)
    private List<OrderDetail> ordersDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "products_image_id", referencedColumnName = "id")
    private ProductsImage productsImage;
}
