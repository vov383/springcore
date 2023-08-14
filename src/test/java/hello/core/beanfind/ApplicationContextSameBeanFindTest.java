package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    /**
     * 이 클래스를 실행하면 SameBeanConfig만 가지고 실행한다.
     * 스프링 빈 2개만 등록해서 실행함
     * */

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        /**
         * 얘를 실행하면 에러 터짐
         * spring 입장에서 빈 뭐 선택할지 몰라서 에러 터짐
         *
         * NoUniqueBeanDefinitionException
         * 빈이 하나만 있어야하는데 2개 나온 것
         * 콘솔
         * org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'hello.core.member.MemberRepository' available: expected single matching bean but found 2: memberRepository1,memberRepository2
         * */

        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
        /**
         * 람다를 사용해서 로직을 만듬
         * 실행하면 성공.. 예외가 터지면 성공한 것
         * */
    }
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1" ,MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    /**
     * 돌려보면 성공
     * config에 파라메터로 1번 2번 정해서 내부의 값 꺼내서 검증하면 확실히 더 검증이 되긴 함.
     * 그렇게 까지 할 필요는 없음.
     * */


    /**
     * 나는 둘다 꺼내고 싶어.
     */
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    /**
     * 단축키
     * ctrl + shift + enter
     * 라인 중간에서 이걸 치면 코드 컴플레이션으로 다음 줄로 넘어감
     * */
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        /**
         * ctrl alt v 로 자동완성하면 Map으로 나운다.
         * */
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);

        /**
         * 테스트 결과
         * key = memberRepository1value = hello.core.member.MemoryMemberRepository@e84a8e1
         * key = memberRepository2value = hello.core.member.MemoryMemberRepository@2e554a3b
         * beansOfType = {memberRepository1=hello.core.member.MemoryMemberRepository@e84a8e1, memberRepository2=hello.core.member.MemoryMemberRepository@2e554a3b}
         * */
    }



    /**
     * class 내부에 class 를 선언하면
     * Scope 를 딱 이 클래스 내부에서만 쓰겠다는 의미
     * */
    @Configuration
    static class SameBeanConfig {
        /**
         * 이름만 다르고 객체의 인스턴스 타입이 같을 수 있다.
         */
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

}
