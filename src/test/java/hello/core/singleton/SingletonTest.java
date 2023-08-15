package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /**
         * 콘솔 출력 결과
         * memberService1 = hello.core.member.MemberServiceImpl@24269709
         * memberService2 = hello.core.member.MemberServiceImpl@2aceadd4
         *
         * 주소값이 다르다.
         * */

        /**
         * 스프링은 웹 어플리케이션
         * 웹 애플리케이션은 고객 요청이 많아.
         * 고객 요청마다 객체 생성? 초당 100개에서 5만개 까지 생성할수도
         * 해결 how? -> 딱 1개만 생성되고 공유하도록
         * 싱글톤 패턴
         * */

        /**
         * 항상 테스트는 sout으로 직접 눈으로 확인X,
         * 자동으로 확인하도록 설계한다.
         * */
        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
//        new SingletonService();
        /**
         * 실행하면 컴파일 오류.
         * java: SingletonService() has private access
         * */
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        /**
        * 주소가 같다.
        * singletonService1 = hello.core.singleton.SingletonService@55fe41ea
        * singletonService2 = hello.core.singleton.SingletonService@55fe41ea
        * */

        assertThat(singletonService1).isSameAs(singletonService2);
        /**
         * isEqualTo ? --> 자바의 .equals() 비교와 같음
         * isSameAs 는? --> 주소값 비교
         * */

        /**
         * 개발할 때 습관적으로 클래스 래밸 테스트를 실행함.
         * 뭐 수정하고 잘못 건드린 거 없는지 테스트
         * */

        /**
         * 그런데 우리가 직접 싱글톤으로 작성할 필요X
         * 스프링 컨테이너를 쓰면 객체를 싱글톤으로 만들어서 다 관리해준다.
         * 초당 몇 개의 요청을 쓰든지 있는 객체를 다 재활용
         * 성능이 좋아진다.
         *
         * */
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때마다 객체 생성
//        MemberService memberService1 = appConfig.memberService();
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);


        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
        /**
         * 조회할 때마다 어떻게?
         * 스프링 로딩될 때
         * 스프링 컨테이너에 등록된 빈을 반환해줌.
         * */
    }
}
