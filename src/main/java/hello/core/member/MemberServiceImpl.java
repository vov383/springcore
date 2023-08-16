package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 직접 @Bean 등록을 해줄 때는 AppConfig 에서 DI 를 해줬는데
     * ComponentScan 을 사용할 때는
     * @Autowired를 생성자에 붙여서
     * 자동으로 연결해서 주입해준다.
     * */
    @Autowired //마치 이렇게 동작함 ==> ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 단축키
     * ctrl + shift + enter ==> 자동완성에서 이걸로 ;까지 한번에 자동완성
     * */

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
