package efub.clone.hanatour.domain.image.repository;

import efub.clone.hanatour.domain.image.domain.Image;
import efub.clone.hanatour.domain.tour.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByTour(Tour tour);
}
