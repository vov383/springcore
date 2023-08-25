package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
/**
 * 이렇게 하면
 * @Qualifier("mainDiscountPolicy") 를 붙였을 때 이 클래스가 동작한다.
 *
 * 어노테이션을 직접 만들 때 장점
 * Qualifier("")해서 직접 ""String으로 작성하다가 오타 발생 시 컴파일 에러 발생 X
 * 반면 애노테이션을 직접 작성하면 컴파일 에러로 발견 cnt
 *
 * 보통 @MainDB 이런 식으로 만들어서 운영한다.
 * @Primary가 있으니까 이걸로 해결되면 이걸로 해도 됨
 * 다양한 상황일 때 애노테이션을 작성해서 해결한다.
 * */
public @interface MainDiscountPolicy {

}
