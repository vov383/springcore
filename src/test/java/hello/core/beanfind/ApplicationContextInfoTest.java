package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {
    /**
     * JUnit5 부터는 class와 method에 public 안 해도 됨.
     * */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " Object = " + bean);
            /**
             * 모든 bean 변수명 가져와서 출력한다.
             * */
        }
        /**
         * 단축키
         * ater + TAB
         * 하면 for문 자동완성
         * */

        /**
         * 단축키
         * soutv + TAB
         * sout 하고 v하면 변수명 자동완성..
         * 엄청 자주 쓰는 단축키다. 알아두자
         * */

    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /**
             * 내가 애플리케이션을 개발하기 위해 등록한 빈,
             * 또는 외부 라이브러리
             * spring 내부에서 등록한 빈이 아닌 것
             * */

            /**
             * ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
             * ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
             * */
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){

            }
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " Object = " + bean);

        }

    }
}
