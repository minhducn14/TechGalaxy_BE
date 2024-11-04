package iuh.fit.se.techgalaxy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "Products_Images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsImage {

    @Id
    @UuidGenerator
    private String id;

    @Column(length = 255)
    private String path;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "color_id", referencedColumnName = "color_id"),
            @JoinColumn(name = "product_variant_detail_id", referencedColumnName = "id")
    })
    private ProductVariantDetail productVariantDetail;
}
