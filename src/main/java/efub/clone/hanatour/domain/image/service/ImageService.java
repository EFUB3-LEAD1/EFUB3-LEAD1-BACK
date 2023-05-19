package efub.clone.hanatour.domain.image.service;

import efub.clone.hanatour.domain.image.domain.Image;
import efub.clone.hanatour.domain.image.repository.ImageRepository;
import efub.clone.hanatour.domain.tour.domain.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    // 특정 Tour에 대한 이미지 목록 조회
    public List<Image> findImageByTour(Tour tour) {
        return imageRepository.findAllByTour(tour);
    }

}
