package iuh.fit.se.techgalaxy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products_Images")
public class ProductsImage {

    @Id
    @UuidGenerator
    private String id;

    @Column(length = 255)
    private String path;

    @OneToOne(mappedBy = "productsImage")
    private ProductVariantDetail productVariantDetail;
}
