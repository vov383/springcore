package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent= 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
            /**
             * 개발할 때 이런 10% 할인 로직은 불안하다.
             * 진짜 어려운 로직
             * 특히 돈 관련된 것
             * 실무에서 이거에 대한 테스트가 엄청 많다.
             *
             * 설계까 잘됐으니까 쉽게 테스트할 수 있는 것.
             * 할인 관련을 별도로 떼어서 만들어놨기 때문에 테스트가 쉬운 것.
             * */
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
