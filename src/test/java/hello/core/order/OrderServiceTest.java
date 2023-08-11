package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        /**
         * primative type을 안 쓰는 이유
         * null 이 들어갈 수 있으려면 Wrapper class 를 써야함
         * 이후에 DB 들어가고 하려면 이걸 신경써야함.
         * */
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        /**
         * Junit 은 sout 으로 찍는게 아니라 junit의 Assertions를 쓴다.
         *
         * */
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
    /**
     * SpringBootTest는 실행하면 스플이 띄우고 해야되서 오래걸린다.
     * 얘 혼자 429ms
     * 그래서 단위테스트가 중요하다.
     * 수천개를 단위테스트 해도 몇초만에 끝남.
     *
     * 단위테스트란?
     * 스프링, 컨테이너의 도움없이 순수하게 자바코드로 테스트하는 것
     * */
}
