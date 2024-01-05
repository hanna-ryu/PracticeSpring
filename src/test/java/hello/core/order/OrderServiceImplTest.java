package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {


    //setter 주입 방식을 선택할시, 현재 갖고있는 의존 관계가 무엇인지 파악해서 그것까지 주입해줘야하는 불편함이 있음.
    //생성자 주입을 선택할 시, 주입해야하는 필드들이 뭐가 있는지 알 수 있음!
    @Test
    void createOrder(){

        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.BASIC));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        
        Order order = orderService.createOrder(1L,"itemA", 10000);
        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(0);

    }

}