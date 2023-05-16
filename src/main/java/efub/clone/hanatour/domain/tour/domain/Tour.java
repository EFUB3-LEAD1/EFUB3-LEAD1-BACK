package efub.clone.hanatour.domain.tour.domain;

import efub.clone.hanatour.domain.heart.domain.Heart;
import efub.clone.hanatour.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tour {

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

    @Column(nullable = false, length = 20)
    private String price;

    @OneToOne(mappedBy = "tour")
    private Plan tourPlan;

    // 좋아요 목록 가져오기
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @Builder
    public Tour(String title, String subTitle, String contents, String price, Category category, Boolean isLayOver, Boolean isShopping) {
        this.title = title;
        this.subTitle = subTitle;
        this.contents = contents;
        this.price = price;
        this.category = category;
        this.isLayOver = isLayOver;
        this.isShopping = isShopping;
    }
}
