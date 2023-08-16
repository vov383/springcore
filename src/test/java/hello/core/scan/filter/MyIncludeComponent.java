package hello.core.scan.filter;

import java.lang.annotation.*;

/**
 * 이 세 가지
 * 이중에 @Target() 이 중요
 * Type은 클래스 레벨에 붙는다.
 *
 * 얘가 붙은 건 컴포넌트 스캔에 주가할 거야 그런 뜻이다.
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
