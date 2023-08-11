package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;


public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        /**
         * 파라메터로 등급만 넘길까, 멤버 자체를 넘길까?
         * 미래 확장성과 몇가지를 고려해서 멤버 자체를 넘기는 것으로 결정.
         * 프로젝트와 상황에 따라 다르다.
         * */

        /**
         * 단일 책임 원칙이 구현 잘 된것.
         * 저장소는 저장소만
         * 주문쪽은 주문만
         * 할인은 할인만
         * 할인정책에 변경이 생기면 할인쪽만 수정하면 된다.
         * */

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
