package efub.clone.hanatour.domain.tour.domain;

import efub.clone.hanatour.domain.image.domain.Image;
import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Boolean isLayOver;

    @Column(nullable = false)
    private Boolean isShopping;

}
