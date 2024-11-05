package iuh.fit.se.techgalaxy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "Img_Product_Feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImgProductFeedback {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_feedback_id")
    private ProductFeedback productFeedback;

    @Column(nullable = false, length = 255)
    private String imagePath;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
