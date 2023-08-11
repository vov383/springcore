package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {

        //given
        /**
         * 이런 환경이 주어졌을 때
         * */
        Member member = new Member(1L, "memberA", Grade.VIP); //vip를 만들었을 때.


        //when
        /**
         * 이렇게 했을 때
         * */
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        /**
         * 1L을 2L로 바꿨더니 실패.
         * 콘솔에 출력된 결과를 보면서 검증하는 것이 아니라 test에서 짚어준다.
         *
         * 테스트 돌렸을 때 실패하면
         * 다른 코드를 추가해서 생긴 경우도 빨리 캐치 가능
         * 현대적인 코딩에서는 테스트가 선택이 아닌 필수
         * 테스트 작성방법을 반드시 알아야 한다.
         * */


        //then
        /**
         * 이렇게 된다.
         * */
        //검증은 Assertions를 사용한다.
        Assertions.assertThat(member).isEqualTo(findMember);
        /**
         * 실행해보면 녹색불이 뜨면서 성공
         * */
    }

}