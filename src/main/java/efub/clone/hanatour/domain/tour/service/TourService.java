package efub.clone.hanatour.domain.tour.service;

import efub.clone.hanatour.domain.spot.domain.Spot;
import efub.clone.hanatour.domain.spot.repository.SpotRepository;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.domain.TourSpot;
import efub.clone.hanatour.domain.tour.dto.TourRequestDto;
import efub.clone.hanatour.domain.tour.repository.PlanRepository;
import efub.clone.hanatour.domain.tour.repository.TourRepository;
import efub.clone.hanatour.domain.tour.repository.TourSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final PlanRepository planRepository;
    private final TourSpotRepository tourSpotRepository;
    private final SpotRepository spotRepository;


    // Tour 저장
    @Transactional
    public Long saveTourInfo(TourRequestDto requestDto) {
        // Tour 저장
        Tour savedTour = tourRepository.save(requestDto.toTourEntity());
        // TourSpot 저장
        Spot findedSpot = spotRepository.findById(requestDto.getSpotId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 지역 ID가 없습니다. ID=" + requestDto.getSpotId()));
        tourSpotRepository.save(
                TourSpot.builder()
                        .tour(savedTour)
                        .spot(findedSpot).build());
        // Plan 저장
        planRepository.save(
                Plan.builder()
                        .beginDate(requestDto.getBeginDate())
                        .endDate(requestDto.getEndDate())
                        .tour(savedTour).build());
        return savedTour.getTourId();
    }
}
