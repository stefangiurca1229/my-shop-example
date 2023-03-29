package com.myshopexample.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshopexample.model.product.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Boolean enable;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createDate;

    @Version
    private Long version;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Stock(){}
    public Stock(int quantity, Double price, Boolean enable, LocalDate createDate, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.enable = enable;
        this.createDate = createDate;
        this.product = product;
    }
}
