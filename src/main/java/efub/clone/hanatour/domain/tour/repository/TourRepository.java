package efub.clone.hanatour.domain.tour.repository;

import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.dto.TourInfoWithImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query(value = "select " +
            "new efub.clone.hanatour.domain.tour.dto.TourInfoWithImageDto(" +
            "tour.tourId, tour.title, tour.subTitle, tour.contents, tour.price, tour.category, image.imageUrl) " +
            "from Tour tour left outer join Image image " +
            "on tour.tourId = image.tour.tourId")
    List<TourInfoWithImageDto> findTourListWithImage();
}
