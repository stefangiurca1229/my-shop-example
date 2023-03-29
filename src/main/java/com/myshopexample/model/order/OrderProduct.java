package com.myshopexample.model.order;

import com.myshopexample.model.product.Category;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_products")
public class OrderProduct{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Double price;
    private String title;
    private String description;
    private String thumbnail;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
