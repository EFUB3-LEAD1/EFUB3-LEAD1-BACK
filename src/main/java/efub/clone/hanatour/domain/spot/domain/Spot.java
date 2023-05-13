package efub.clone.hanatour.domain.spot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    @Column(nullable = false, length = 64)
    private String continent;

    @Column(nullable = false, length = 64)
    private String nation;
}
