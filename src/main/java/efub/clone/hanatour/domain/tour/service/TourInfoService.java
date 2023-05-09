package efub.clone.hanatour.domain.tour.service;

import efub.clone.hanatour.domain.image.domain.Image;
import efub.clone.hanatour.domain.image.service.ImageService;
import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.domain.spot.repository.SpotRepository;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.domain.TourSpot;
import efub.clone.hanatour.domain.tour.dto.TourInfoDetailsDto;
import efub.clone.hanatour.domain.tour.dto.TourInfoDto;
import efub.clone.hanatour.domain.tour.dto.TourListResponseDto;
import efub.clone.hanatour.domain.tour.dto.TourRequestDto;
import efub.clone.hanatour.domain.tour.repository.PlanRepository;
import efub.clone.hanatour.domain.tour.repository.TourRepository;
import efub.clone.hanatour.domain.tour.repository.TourSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourInfoService {

    private final TourRepository tourRepository;
    private final PlanRepository planRepository;
    private final TourSpotRepository tourSpotRepository;
    private final SpotRepository spotRepository;

    private final ImageService imageService;

    // Tour 저장
    @Transactional
    public void saveTourInfo(TourRequestDto requestDto) {
        // Tour 저장
        Tour savedTour = tourRepository.save(requestDto.toTourEntity());
        // TourSpot 저장
        Spot foundSpot = spotRepository.findById(requestDto.getSpotId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 지역 ID가 없습니다. ID=" + requestDto.getSpotId()));
        tourSpotRepository.save(
                TourSpot.builder()
                        .tour(savedTour)
                        .spot(foundSpot).build());
        // Plan 저장
        planRepository.save(
                Plan.builder()
                        .beginDate(requestDto.getBeginDate())
                        .endDate(requestDto.getEndDate())
                        .tour(savedTour).build());
    }

    // 특정 Tour의 상세 정보 조회
    @Transactional(readOnly = true)
    public TourInfoDetailsDto findTourById(Long tourId) {
        // Tour 찾기
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 여행 상품입니다. ID=" + tourId));

        // Spot 찾기
        Spot spot = findSpotByTour(tour);
        // Image 찾기
        List<Image> imageList = imageService.findImageByTour(tour);

        // DTO 생성 및 리턴
        return TourInfoDetailsDto.of(tour, spot, tour.getTourPlan(), imageList);
    }

    // Tour에 대한 Spot 조회
    private Spot findSpotByTour(Tour tour) {
        // TourSpot 찾기
        TourSpot foundTourSpot = tourSpotRepository.findByTour(tour);
        // TourSpot의 spotId로 Spot 찾기
        Long spotId = foundTourSpot.getSpot().getSpotId();
        return spotRepository.findById(spotId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 여행지입니다. ID=" + spotId));
    }

    // Tour 목록 조회
    public TourListResponseDto findTourList() {
        List<Tour> tourList = tourRepository.findAll();
        List<TourInfoDto> tourInfoDtoList = tourList.stream()
                .map(tour -> TourInfoDto.of(tour, tour.getTourPlan())).collect(Collectors.toList());
        return TourListResponseDto.of("파리", tourInfoDtoList);
    }
}
