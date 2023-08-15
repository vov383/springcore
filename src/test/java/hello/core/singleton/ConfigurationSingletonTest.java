package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        /**
         * memberRepository 를 두번 new한 셈인데 둘이 같은지 다른지 확인하자
         *
         * 실행 시 콘솔 결과
         * memberService -> memberRepository = hello.core.member.MemoryMemberRepository@53f0a4cb
         * orderService -> memberRepository = hello.core.member.MemoryMemberRepository@53f0a4cb
         * memberRepository = hello.core.member.MemoryMemberRepository@53f0a4cb
         *
         * 세 스프링 빈이 다 같은 놈이다.
         * 이게 어케 됨?
         *
         * bean 메서드에 로그를 남기고 실행했더니
         * call AppConfig.memberService
         * call AppConfig.memberRepository
         * call AppConfig.orderService
         *
         * 의도와 다르게 호출됨.
         * */

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        /**
         * 실행해보면 클래스명 뒤에 이상한 게 붙었다.
         *
         * bean.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ccc7ff19
         * 객체 인스턴스?
         * 얘가 만든 클래스 정보
         * */

        /**
         * AppConfig 에서 @Configuration 삭제하고 테스트 실행 결과
         * bean.getClass() = class hello.core.AppConfig
         * 내가 작성한 순수한 AppConfig 실행.
         * CGLib 안씀
         * @bean 도 4개 다 등록됨
         * 근데 call AppConfig.memberRepository 가 3번 호출
         * 다른 싱글톤이 깨졌다.
         *
         * 다른 테스트 실행해보면
         * 당연히 에러나고
         * memberService -> memberRepository = hello.core.member.MemoryMemberRepository@5a1de7fb
         * orderService -> memberRepository = hello.core.member.MemoryMemberRepository@335b5620
         * memberRepository = hello.core.member.MemoryMemberRepository@29a0cdb
         *
         * memberRepository 가 다르 객체로 등록됨.
         *
         * 또 다른 문제
         * 내가 작성한 MemberServiceImpl, OrderServiceImpl 은 스프링 빈 등록 X
         * AppConfig 에서 생짜 자바 코드를 호출해버리면 내가 직접 new 한 객체 호출함.
         *
         * @Configuration 대신에 @Autowired MemberService memberService; 해서
         * 빈 등록하고 자동으로 DI 해주면 됨.
         * new 할 때 spring에서 빈을 끌어와서 주입 해준다.
         *
         * Autowired 에 대해서는 뒤에서 심도있게 배울 예정.
         * */
    }
}
