package efub.clone.hanatour.domain.tour.service;

import efub.clone.hanatour.domain.image.domain.Image;
import efub.clone.hanatour.domain.image.service.ImageService;
import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.domain.spot.repository.SpotRepository;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.domain.TourSpot;
import efub.clone.hanatour.domain.tour.dto.*;
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
        List<Spot> spot = spotRepository.findAllByTour(tour.getTourId());
        // Image 찾기
        List<Image> imageList = imageService.findImageByTour(tour);

        // DTO 생성 및 리턴
        return TourInfoDetailsDto.of(tour, spot, tour.getTourPlan(), imageList);
    }

    // Tour 목록 조회 - 전체 패키지
    public List<TourInfoWithImageDto> findTourListWithImage() {
        return tourRepository.findTourListWithImage().stream().distinct().collect(Collectors.toList());
    }

    // Tour 목록 조회 - 검색
    public TourListResponseDto findTourListByNation(String nation) {
        // nation(국가명)으로 spot 찾기
        Spot spot = spotRepository.findByNation(nation);
        // 찾은 spot의 id로 tour_spot 목록 찾기 + tour_spot과 매핑되는 tour 찾기
        List<Tour> tourList = tourSpotRepository.findAllBySpot(spot)
                .stream().map(TourSpot::getTour).collect(Collectors.toList());
        // 데이터 형식 변환
        List<TourInfoDto> tourInfoDtoList = tourList.stream()
                .map(tour -> TourInfoDto.of(tour, tour.getTourPlan())).collect(Collectors.toList());
        return TourListResponseDto.of(nation, tourInfoDtoList);
    }
}
