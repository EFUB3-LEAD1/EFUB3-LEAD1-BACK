package efub.clone.hanatour.domain.image.domain;

import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Image를 엔티티로 구현하는 경우 (-> Member에서 OneToMany)
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "url", nullable = false)
    private String imageUrl;
}
