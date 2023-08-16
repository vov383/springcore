package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        /**
         * AppConfig.java 에도 @Configuration 이 달려있음.
         * 얘랑 충도 일어나는 것 방지
         *
         * @Configuration 안으로 들어가면 안에 @Component 가 붙어있어서 자동 스캔 컴포넌트 등록 대상이다.
         * 예제를 안전하게 유지하기 위해 필터로 제외시켜
         * */
)
/**
 * 설정 정보니까 @Configuration
 *
 * ComponentScan ?
 * 스프링 빈을 쫙 긁어서 자동으로 스프링 컨테이너로 끌어와
 * @ComponentScan 하면 끝
 * */
public class AutoAppConfig {

    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        /**
         * MemoryMemberRepository.java 는 컴포넌트.
         * memoryMemberRepository 로 bean 자동 등록
         *
         * 자동등록과 수동등록 두 개 충돌인데 왜 테스트 성공?
         * */
    }
}
