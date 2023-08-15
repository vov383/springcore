package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
//        statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
//        statefulService2.order("userB", 20000);
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        /**
         * userA 는 조회 결과 10000원을 기대했는데 20000원이 나옴
         * 왜?
         * statefulService1 이든 statefulService2 든 같은 객체를 쓰니까.
         * 이런 장애는 실제로 잡기도 어렵다.
         *
         * 보통 getPrice() 할 때 문제가 생김.
         * */

        /**
         * 공유필드를 사용하지 않도록 수정하고
         * 지역변수로 price를 넘기도록 로직을 설정했더니 문제 해결.
         *
         * 실무얘기
         * 너무 중요함
         * 이런 문제가 터지면 내 아이디에서 다른 사람 아이디가 보임
         * 내 결제내역에 다른 사람의 결제 내역이 보임
         *
         * 이런 문제는 복구하는데 몇달 걸림
         * 로그 다 까서 수장해야함
         *
         * 검색관련
         * 이상하게 로직이 다르게 나옴
         * 큐에서도 못잡아
         * 이거는 보나마나 멀티쓰레드 문제다.
         *
         * 딱 눈에 보이는 게 아님
         * 상속관계로 되어 있어서 부모에서 이걸 하고 있으면 단순히 못찾는다.
         * 몇 년에 한번씩 꼭 나옴.
         *
         * 공유필드 조심!!
         * 스프링 빈은 꼭 무상태로 설계!!!
         * */

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}