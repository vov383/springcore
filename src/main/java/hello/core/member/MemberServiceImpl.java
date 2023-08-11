package hello.core.member;

/**
 * 관례상
 * Service에 해당하는 impl 이 하나만 있을 때는
 * Service명 + Impl
 * */
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 단축키
     * ctrl + shift + enter ==> 자동완성에서 이걸로 ;까지 한번에 자동완성
     * */

    /**
     * MemberServiceImpl 의 문제점
     * MemberRepository 는 인터페이스에 의존하니까 괜찮은데,
     * MemoryMemberRepository (실제 할당하는 부분)는 구현체에 의존하고 있음.
     *
     * MemberServiceImpl 가 추상화에도 의존하고 구현화에도 의존하는 문제점 발생.
     * DIP 위반. ==> 나중에 변경할 때 문제가 된다.
     * */

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        /**
         * 다형성에 의해서
         * interface가 아니라
         * Override된 MemoryMemberRepository의 save()가 호출된다.
         * */
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
