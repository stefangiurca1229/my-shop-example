package com.myshopexample.model.bascket;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class BascketProductKey implements Serializable {
    @Column(name = "bascket_id")
    private Long bascketId;
    @Column(name = "product_id")
    private Long productId;
}
