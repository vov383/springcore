package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import hello.core.member.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        /**
         * Spring은 시작할때 모두 ApplicationContext 로 시작한다.
         *
         * AnnotationConfigApplicationContext
         * 어노테이션 기반으로 Configration 하니까
         *
         * 그리고 paprameter로 AppConfig.class 를 넣어준다.
         * AppConfig에 들어있는 환경 설정정보를 가지고 스프링이 @Bean 붙은 걸 DI 컨테이너에 넣어서 관리해준다.
         * */
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        /**
         * 기본적으로 Bean은 메서드명으로 등록되어 있다.
         * 나는 memberService를 찾을 거야.
         * Type은 MemberService 야.
         * */

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

        /**
         * 실행히보면 기존이랑 달라진 것이 있다.
         * 콘솔에 맨 위에 5개 bean은 스프링에 필요해서 생성된 Bean이고
         * 그 밑에 appConfig, memberService, memberRepository, orderService, discountPolicy 는
         * 내가 스프링 컨테이너에 @Bean으로 등록한 것
         * 꺼낼 때는 메서드명과 type으로 꺼낸다.
         * */
    }
}
