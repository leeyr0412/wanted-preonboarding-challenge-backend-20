package com.wanted.market.domain.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.market.api.controller.response.ProductResponse;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static com.wanted.market.domain.product.QProduct.product;

@Repository
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<ProductResponse> getAllProducts() {
        return queryFactory.select(constructor(ProductResponse.class,
                        product.id,
                        product.productName,
                        product.price,
                        product.state))
                .from(product).fetch();
    }
}
