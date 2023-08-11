package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    /**
     * 테스트 결과가 한글로 나오도록
     * junit5 부터 적용됨.
     * */
    void vip_o(){
        //givne

        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
        /**
         * Assertions 는 static import 하는 것이 좋다.
         * 보기에도 간결해지고
         * 다음부터 Assertions를 그대로 쓸 수 있다.
         * static import 는 Java 기본문법 이다.
         * */


    }


    /**
     * Test는 실패 테스트도 꼭 만들어봐야함.
     * */

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        //assertThat(discount).isEqualTo(1000);
        assertThat(discount).isEqualTo(0);
    }

}