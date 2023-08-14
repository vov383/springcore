package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        /*항상 검증은 Assertions 사용*/
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        /**
         * 인터페이스 아니어도 됨.
         * 구현된 객체로 조회 가능
         * but, 나쁘다.
         * 항상 역할과 구현을 구분해야함.
         * 좋은 코드는 아니지만 살다보면 모든게 이상적으로 돌아가지 않을 수 있으니까.
         * 그럴 때 쓰면 된다.
         * */
    }

    /**
     * 항상 테스트는 실패 테스트를 만들어야함.
    */
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByNameX(){
//        ac.getBean("XXXXX", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXXX", MemberService.class));
        /**
         * assertThrows(--Exception.class)
         * 이 익셉션이 나면 람다를 실행해라
         * */
    }
    /**
     * 빈 이름으로 조회했는데 없으면?
     * NoSuchBeanDefinitionException: No bean named 'XXXXX' available
     * */

    /**
     * 단축키
     * static import
     * 스태틱 import 하고 싶은 곳.에 커서 놓고 alt + Enter 한 다음에 static 골르면 됨.
     * */

}
