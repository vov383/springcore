package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        /**
         * 실행하면
         * NoUniqueBeanDefinitionException 나니까 예외처리
         * */
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy" ,DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        /**
         * 타입은 DiscountPolicy
         * 실제 구현객체는 RateDiscountPolicy
         * */
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
        /**
         * 구체적인 타입으로 딱 지정
         * 물론 나쁜 방식
         *
         * */
    }

    @Test
    @DisplayName("부모타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
            /**
             * 참고로
             * 현업에서 콘솔에 출력을 sout으로 남기면 안된다.
             * 테스트 통과 결과를 시스템으로 진단해야함
             * println 같은 건 다 빼야함.
             * Test 자체를 디버깅해볼 수 있으니까 그때만 남겨도 괜찮음.
             * */
        }
    }

    @Test
    @DisplayName("부모타입으로 모두 조회하기 -> Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        /**
         * 스프링 내부에 있는 모든 bean까지 다 튀어나옴
         * 왜?
         * 자바는 모든 게 다 Object type 이기 때문에
         * */

        /**
         * 지금까지 조회하는 기능 모두 다 봣음.
         * 나머지 기능이 있어도 잘 안씀
         * 이정도만 알아두면 된다.
         * 우리가 실제 개발할 때 ApplicationConfig에서 getBean 할 일이 잘 없어.
         * 뒤에 가면 스프링 컨테이너가 자동으로 DI 해주는 걸 쓰거나 함
         *
         * 이걸 설명한 이유
         * 이건 기본 기능
         * 가끔 순수한 자바 애플리케이션에서 스프링 컨테이너를 생성해서 써야할 일이 있다.
         * 그럴 때 활용한다.
         * */
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
            /**
             * 리턴 타입을 DiscountPolicy 로 하는 것과
             * RateDiscountPolicy 로 하는 것의 차이
             *
             * 역할과 구현을 구분하라
             * DiscountPolicy 로 하는 것이 훨씬 났다
             * 다른데서 DI 할 때 DiscountPolicy를 inject 하니까
             * */
        }


        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
