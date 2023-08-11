package hello.core;

import hello.core.member.Grade;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import hello.core.member.Member;


public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        /**
         * 이 사람이 제대로 가입됐는지 확인해보면 됨.
         * */

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        /** 
         * 이제 둘 이름을 비교
         * 지금 순수한 자바코드로 구현 완료.. 스프링 관련 X
         * 
         * 단, 애플리케이션 로직으로 확인하는것은 좋지 않아.
         * junit으로 테스트!!
         * test 하위에 패키지 생성
         * main과 test는 디렉토리가 분리되어 있다.
         * test는 빌드될 때 빠진다.
         * */

    }
}
