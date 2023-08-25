package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 롬복이
 * getter, setter 메서드를
 * annotationProcessing 으로 자동으로 만들어준다.
 * 귀찮으니까
 * getter는 진짜 많이 쓴다.
 *
 * 생성자도 가능
 * 롬복 검색해보면 뭐 많이 나옴
 * 기본으로 많이 쓴다.
 * */
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
