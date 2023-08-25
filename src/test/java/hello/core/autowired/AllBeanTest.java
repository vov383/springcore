package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

        /**
         * TDD로 개발
         *
         * 장점
         * 스프링 빈 이름이랑 맞추면
         * 굉장히 편리하게 다형성 코드를 완성시킬 수 있다.
         * 동적으로 bean 을 선택해야 할 때
         * map으로 받아서 넘기면 다형성 코드로 사용할 수 있다.
         * */
    }



    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
            /**
             * ==출력결과==
             * policyMap = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@56c698e3, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@47a86fbb}
             * policyList = [hello.core.discount.FixDiscountPolicy@56c698e3, hello.core.discount.RateDiscountPolicy@47a86fbb]
             *
             * map은 키네임 있고, list는 이름이 없이 출력됨
             * */
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
