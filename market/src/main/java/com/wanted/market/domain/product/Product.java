package com.wanted.market.domain.product;

import com.wanted.market.domain.BaseEntity;
import com.wanted.market.domain.users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`product`")
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Users seller;

    private String productName;
    private Integer price;
    private ProductState state;

    @Column(nullable = false)
    private Integer count;

    @Builder
    private Product(Long id, Users seller, String productName, Integer price, ProductState state, Integer count) {
        this.id = id;
        this.seller = seller;
        this.productName = productName;
        this.price = price;
        this.state = state;
        this.count = count;
    }
}
