package efub.clone.hanatour.domain.spot.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spot_id;

    @Column(nullable = false, length = 64)
    private String continent;

    @Column(nullable = false, length = 64)
    private String nation;
}
