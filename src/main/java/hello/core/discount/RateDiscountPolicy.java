package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
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
