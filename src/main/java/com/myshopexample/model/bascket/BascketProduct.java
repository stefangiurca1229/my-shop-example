package com.myshopexample.model.bascket;

import com.myshopexample.model.product.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bascket_products")
public class BascketProduct {
    @EmbeddedId
    private BascketProductKey bascketProductKey = new BascketProductKey();
    private int quantity ;
    @ManyToOne
    @MapsId("bascketId")
    @JoinColumn(name = "bascket_id")
    private Bascket bascket;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
}
