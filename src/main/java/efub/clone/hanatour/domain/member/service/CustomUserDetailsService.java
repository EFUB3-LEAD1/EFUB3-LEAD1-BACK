package efub.clone.hanatour.domain.member.service;

import efub.clone.hanatour.domain.member.entity.Member;
import efub.clone.hanatour.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        UserDetails userDetails =  memberRepository.findMemberInfoByAccount(account)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(account + " -> 데이터베이스에서 찾을 수 없습니다."));
        log.info(userDetails.getUsername().toString());
        return userDetails;
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

        User user =  new User(
                member.getAccount(),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );

        return user;
    }
}
