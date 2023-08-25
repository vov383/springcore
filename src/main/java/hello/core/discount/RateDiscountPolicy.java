package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
/**
 * 우리는 빈 2개중에 얘가 먼저 선택되게 할 거야.
 * 얘는 어떤 경우에 많이 써?
 * DB 커넥션 객체가 있을 때 메인 DB와 보조 DB가 있어.
 * 메인의 로직이 90퍼 보조가 10퍼
 * 둘에 @Qualifier 붙이기는 귀찮아.
 * 메인 DB는 그냥 @Primary 쓰고 보조 DB 가져올 때만 Qualifier를 쓰든 해서 가져오는 걸로 룰을 정해.
 * */
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent= 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
    /**
     * 코드를 작성하고
     * 단축키
     * ctrl + shift + T
     * 테스트 만드는 툴 open
     * 이름도 클래스명 + Test 로 만들어짐.
     * 건드릴 필요없이 OK
     * */
}
