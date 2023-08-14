package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    /**
     * 참고로 필드에 GenericXmlApplicationContext 를 안쓰고 ApplicationContext 를 사용해서 선언하면
     * getBeanDefinition() 을 못한다.
     * */



    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }

        }
        /**
         * 실행해보면 bean 을 볼 수 있음.
         * memberService 보면 scope 없고
         * lazyInit : 보통은 스프링 빈은 스프링 띄울 때 등록하는데 lazyInit 은 나중에 bean을 사용할 때 등록해 하는 명령어.
         *
         * 실행 결과 콘솔
         * beanDefinitionName = memberService beanDefinition = Root bean: class [null];
         * scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false;
         * factoryBeanName=appConfig; factoryMethodName=memberService; initMethodName=null; destroyMethodName=(inferred);
         * defined in hello.core.AppConfig
         *
         * factoryBeanName=appConfig; factoryMethodName=memberService;
         * appConfig 에 있는 memberService 를 호출할 수 있다는 뜻
         *
         * 이런 메타정보가 있고 메타정보를 기반으로 실제 인스턴스를 생성할 수 있다.
         * 이런 정보들이 있다 하고 이해하면 됨
         *
         * 내가 직접 new 해서 빈을 스프링 컨테이너에 등록할 수도 있다.
         * 실무에서 BeanDefinition을 직접 등록할 일은 거의 없다.
         *
         * 스프링의 다양한 설정정보를 BeanDefinition을 사용해서 추상화 해서 사용한다 정도만 기억하면 된다.
         * 스프링 라이브러리를 활용하는 오픈소스들을 보거나 하면 이걸 활용하는 경우를 볼 수 있다.
         *
         * xml과 달리 class 정보가 null, factoryBean을 통해서 생성되고 factoryMethodName에 name정보가 나온다.
         * 잘 몰라도 괜찮다. 참고로 알아두면 된다.
         * */

        /**
         * GenericXmlApplicationContext 로 실행하면 좀 다르다.
         * 얘는 제너릭 빈이라고 빈에 대한 정보가 명확하게 등록되어 있다.
         *
         * beanDefinitionName = memberService beanDefinition = Generic bean: class [hello.core.member.MemberServiceImpl];
         * scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true;
         * primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null;
         * defined in class path resource [appConfig.xml]
         * */

        /**
         * 스프링 빈을 등록하는 방법 2가지
         * 1. 직접 bean을 스프링에 등록
         * 2. 팩토리 메서드를 통해서 등록
         *      -> AppConfig 의 메서드를 톸해서 등록함. @Configration 방식이 이 방식이다.
         * */

    }
}
