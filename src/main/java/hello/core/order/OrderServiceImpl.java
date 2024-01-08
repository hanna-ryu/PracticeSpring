package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@RequiredArgsConstructor // 생성자 대체
@Component
public class OrderServiceImpl implements OrderService{

    /*
    * 생성자 주입을 쓰면, final 키워드를 붙여서 생성자에서 필수필드 작성 누락이 되었을때 컴파일 에러가 나게 할 수 있음=> 정말 좋은 에러! */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 필드주입 : 쓰지말자!
   인터페이스에만 의존 / 구체적인 클래스는 전혀 모름 => DIP 위반, 또한 외부에서 변경 불가
   private final   MemberRepository memberRepository;
  private final  DiscountPolicy discountPolicy;
    */

    //생성자가 딱 1개만 있다면, @Autowired가 자동 주입됨.
    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) { // Qualifier로 이름을 지정해서 매칭하기
    public OrderServiceImpl(MemberRepository memberRepository,  DiscountPolicy discountPolicy) { // @Primary 어노테이션을 활용해서 가장 먼저 빈으로 가져오기
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /* 아래는 Setter 주입
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDisCountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
        }

     */

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
