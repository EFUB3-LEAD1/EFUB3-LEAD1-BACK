package efub.clone.hanatour.domain.tour.domain;

import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTable(
        name = "tour_image",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "tour_image_id")
)
public class Tour extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourId;

    @Column(nullable = false, length = 64)
    private String title;

    @Column(length = 64)
    private String subTitle;

    @Column(length = 64)
    private String contents;

    // image를 value로 생각하고 구현
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tour_image",
            joinColumns = @JoinColumn(name = "tour_id"))
    private List<TourImage> images;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean isLayOver;

    @Column(nullable = false)
    private Boolean isShopping;
}
