package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store =new HashMap<>();
    /**
     * 아직 db 확정X,
     * 개발 진행 can
     * 단, 테스트용으로만 사용 가능. 메모리에만 저장되니까.
     *
     * 일단은 HashMap을 썼는데, 실무에서는 ConcurrentHashMap<>()을 쓰는 게 맞다.
     * 동시성 이슈 발생 때문에.. 따로 공부 필요
     * 예제니까 간략하게 작성한 것. 
     * */
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
