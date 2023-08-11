package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 애플리케이션의 설정정보(구성정보)를 담당한다는 의미
 * 스프링에서는 @Configuration를 달아서 표시한다.
 * */
public class AppConfig {

    @Bean
    /**
     * 각 메서드에 @Bean을 붙여준다.
     * 메서드들이 SpringContainer에 등록됨.
     *
     * @Bean(name="mmm") 이렇게 쓰면 이름 변경도 가능
     * 웬만하면 바꾸지 마라
     * 관례상 디폴트를 따르는 것이 좋다.
     * 특별한 경우가 아니면 디폴트로
     * */
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
