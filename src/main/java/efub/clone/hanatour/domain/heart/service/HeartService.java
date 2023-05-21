package efub.clone.hanatour.domain.heart.service;

import efub.clone.hanatour.domain.heart.domain.Heart;
import efub.clone.hanatour.domain.heart.repository.HeartRepository;
import efub.clone.hanatour.domain.member.entity.Member;
import efub.clone.hanatour.domain.member.repository.MemberRepository;
import efub.clone.hanatour.domain.member.util.SecurityUtil;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.dto.PlanDto;
import efub.clone.hanatour.domain.tour.dto.TourInfoDto;
import efub.clone.hanatour.domain.tour.repository.TourRepository;
import efub.clone.hanatour.domain.tour.service.TourInfoService;
import efub.clone.hanatour.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {
    private final TourInfoService tourInfoService;
    private final TourRepository tourRepository;
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public void createHeart(String token, Long tourId) {

        // 인증 정보로부터 회원 정보 받아오기
        String account = SecurityUtil.getCurrentMemberAccount();

        Member member = memberRepository.findMemberInfoByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member"));

        // 요청 데이터로부터 여행 정보 받아오기
        Tour tour = tourRepository.findByTourId(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tour"));

        // 이미 좋아요 했는지 확인
        if (heartRepository.existsByMemberAccountIdAndTour(member, tour)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 여행입니다.");
        }

        Heart heart = Heart.builder()
                .member(member)
                .tour(tour)
                .build();

        // db에 저장
        heartRepository.save(heart);
    }


    public void deleteHeart(String token, Long tourId) {
        // 토큰에서 회원 정보를 추출
        String account = SecurityUtil.getCurrentMemberAccount();

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 접근입니다."));
        Member member = memberRepository.findMemberInfoByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        Heart heart = heartRepository.findByTourAndMemberAccountId(tour, member);

        heartRepository.delete(heart);
    }

    public List<TourInfoDto> getHeartTours(String token) {
        String account = SecurityUtil.getCurrentMemberAccount();
        Member member = memberRepository.findMemberInfoByAccount(account)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

//        List<Heart> hearts = heartRepository.findByMemberAccountId(member.getMemberId());
        List<Heart> hearts = heartRepository.findByMemberAccountId(member);

        List<TourInfoDto> heartTours = hearts.stream()
                .map(heart -> {
                    Tour tour = heart.getTour();
                    Plan tourPlan = tour.getTourPlan();
                    return TourInfoDto.builder()
                            .tourId(tour.getTourId())
                            .title(tour.getTitle())
                            .subTitle(tour.getSubTitle())
                            .contents(tour.getContents())
                            .price(tour.getPrice())
                            .category(tour.getCategory())
                            .tourPlan(PlanDto.of(tourPlan))
                            .isHeart(true)
                            .build();
                })
                .collect(Collectors.toList());

        return heartTours;
    }

}
