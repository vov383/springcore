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
public class AppConfig {

    @Bean
    /**
     * @Bean memberService -> new MemoryMemberRepository() 호출함
     * @Bean orderService -> new MemoryMemberRepository()
     *
     * 이러면 싱글톤이 깨지는 것 아니야?
     * 고민되면 Test 코드로 돌려봐
     *
     * bean 메서드에 로그를 남겨보자
     * call AppConfig.memberService 호출됨
     * call AppConfig.memberRepository
     * call AppConfig.memberRepository
     * call AppConfig.orderService
     * call AppConfig.memberRepository
     *
     * 최종적으로 memberRepository가 3번 호출되어야 한다.
     *
     * 참고로 메서드 호출의 순서는 보장하지 않음.
     *
     * 테스트로 실행 결과
     * call AppConfig.memberService 호출됨
     * call AppConfig.memberRepository
     * call AppConfig.orderService
     *
     * 메서드가 한번 호출됨
     * 이게 스프링이 뭔가 싱글톤을 보장해준다는 것을 알 수 있음.
     * 자바 코드로는 설명 안됨.
     *
     * */
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        /**
         * 단축키
         * soutm + Tab
         * */
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
