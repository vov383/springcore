package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
        /**
         * 실행 결과
         * find prototypeBean1
         * PrototypeBean.init
         * find prototypeBean2
         * PrototypeBean.init
         * prototypeBean1 = hello.core.scope.PrototypeTest$PrototypeBean@1fc32e4f
         * prototypeBean2 = hello.core.scope.PrototypeTest$PrototypeBean@2f67b837
         *
         * 실행해보니 서로 다른 주소값이 나왔다.
         * 그리고 destroy 실행 안됨.
         * 왜? prototype 이니까.
         *
         * 그리고 destroy 안나옴
         * */
    }

    @Scope("prototype")
    /**
     * 얘는 @ComponentBean 애노테이션이 없어?
     * ac 로 객체를 new 할 때 bean으로 등록을 했으니까 없어도 됨.
     * */
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");

        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }

    }


}

