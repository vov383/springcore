package hello.core.singleton;

import org.junit.jupiter.api.Test;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    /**
     * 관례상 싱글톤 쓰는 법
     * 자기자신을 내부에  private static 으로 가지고 있음.
     * 클래스 레벨에 올라가기 때문에 딱 하나만 존재함 (자바 기본)
     * */

    public static SingletonService getInstance(){
        return instance;
        /**
         * JVM이 뜰 때 SingletonService 오른쪽에 new 되어 있으니
         * 내부적으로 실행해서 자기자신을 객체로 가지고 있으면서 instance에 참조를 넣으둠
         * */
    }

    private SingletonService(){
        /**
         * 내가 감추려고 해도 누가 외부에서 new 해서 생성하면 어떡함?
         * 그래서 private 생성자를 쓴다.
         * 생성자 막으려고
         *
         * 이렇게 하면 SingletonService 를 new 할수 있는 건 SingletonService 밖에 없어.
         * SingletonService 를 생성할 수 있는 건 아무것도 없어.
         * */
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
