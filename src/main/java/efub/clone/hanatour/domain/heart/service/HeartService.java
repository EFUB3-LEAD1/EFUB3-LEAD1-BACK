package efub.clone.hanatour.domain.heart.service;

import efub.clone.hanatour.domain.heart.domain.Heart;
import efub.clone.hanatour.domain.heart.dto.HeartRequestDto;
import efub.clone.hanatour.domain.member.domain.dto.MemberInfoRequestDto;
import efub.clone.hanatour.domain.heart.repository.HeartRepository;
import efub.clone.hanatour.domain.member.domain.dto.MemberRequestDto;
import efub.clone.hanatour.domain.member.domain.entity.Member;
import efub.clone.hanatour.domain.member.domain.repository.MemberRepository;
import efub.clone.hanatour.domain.member.domain.service.MemberService;
import efub.clone.hanatour.domain.member.domain.util.SecurityUtil;
import efub.clone.hanatour.domain.tour.domain.Plan;
import efub.clone.hanatour.domain.tour.domain.Tour;
import efub.clone.hanatour.domain.tour.dto.PlanDto;
import efub.clone.hanatour.domain.tour.dto.TourInfoDto;
import efub.clone.hanatour.domain.tour.repository.TourRepository;
import efub.clone.hanatour.domain.tour.service.TourInfoService;
import efub.clone.hanatour.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        // 토큰 유효성 검사
        /*
        if (!tokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        // 토큰으로부터 인증 정보 받아오기
        Authentication authentication = tokenProvider.getAuthentication(token);

         */

        // 인증 정보로부터 회원 정보 받아오기
        String account = SecurityUtil.getCurrentMemberAccount();
        log.info(account);
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
        // 인증 정보로부터 회원 정보 받아오기
        String account = SecurityUtil.getCurrentMemberAccount();
        log.info(account);
        Member member = memberRepository.findMemberInfoByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member"));

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tour Id"));

//        Heart heart = heartRepository.findFirstByMemberAccountIdAndTour(member, tour)
//                .orElseThrow(() -> new IllegalArgumentException("이미 좋아요를 누른 여행입니다."));
        Optional<Heart> heartOptional = heartRepository.findByMemberAccountIdAndTour(member, tour);

        if (heartOptional.isPresent()) {
            // db에서 삭제
            Heart heart = heartOptional.get();
            heartRepository.delete(heart);
        } else {
            throw new IllegalArgumentException("좋아요가 존재하지 않습니다.");
        }

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
                    boolean isHeart = true;
                    return TourInfoDto.builder()
                            .tourId(tour.getTourId())
                            .title(tour.getTitle())
                            .subTitle(tour.getSubTitle())
                            .contents(tour.getContents())
                            .price(tour.getPrice())
                            .category(tour.getCategory())
                            .tourPlan(PlanDto.of(tourPlan))
                            .isHeart(isHeart)
                            .build();
                })
                .collect(Collectors.toList());

        return heartTours;
    }

}
