package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService{


    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     *
    OrderServiceImpl이 추상(DiscountPolicy)에만 의존해야 하는데,
     추상뿐만 아니라 구체(FixDiscountPolicy)에도 의존하고 있음
        >> DIP 위반
     -해결
     1. 구체클레스를 변경할 때 클라이언트 코드(OrderServiceImpl)도 함께 변경해야 한다. 추상에만 의존하도록 변경!

     **/
    private DiscountPolicy discountPolicy;



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        //회원찾기
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice,discountPrice);

    }
}
