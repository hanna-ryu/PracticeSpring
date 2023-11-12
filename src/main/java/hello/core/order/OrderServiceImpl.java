package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //인터페이스에만 의존 / 구체적인 클래스는 전혀 모름 => DIP
    @Autowired private  MemberRepository memberRepository;
    //인터페이스에만 의존 / 구체적인 클래스는 전혀 모름 => DIP
    @Autowired private  DiscountPolicy discountPolicy;

    //생성자가 딱 1개만 있다면, @Autowired가 자동 주입됨.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    public void setDisCountPolicy(DiscountPolicy discountPolicy){
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//        }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
