package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {
    
    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        /**
         * ApplicationContext 가 부모라서 이렇게 씀
         * */
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        /**
         * 아직 테스트만 만들어서 실행하면 에러
         * xml은 main.resources에 만든다.
         * 자바 코드가 아닌 건 모두 resources.
         * */

        /**
         * 실행해보면
         * resources 밑에 xml을 읽어서 동작한다.
         * 싱글톤 빈으로 등록된 것을 알 수 있다.
         * */
    }
}
