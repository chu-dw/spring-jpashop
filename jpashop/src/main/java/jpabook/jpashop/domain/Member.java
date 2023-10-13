package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private  String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")  //오더 테이블의 멤버에 의해 매핑된 거울
    private List<Order> orders = new ArrayList<>();

}
