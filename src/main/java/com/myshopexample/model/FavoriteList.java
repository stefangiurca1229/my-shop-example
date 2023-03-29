package com.myshopexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshopexample.model.customer.Customer;
import com.myshopexample.model.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class FavoriteList implements Serializable{
    @Id
    private Long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @MapsId
    private Customer customer;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "favoriteLists_products",
            joinColumns = @JoinColumn(name = "favoriteList_id",referencedColumnName = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
    )
    private Set<Product> favoriteProducts = new HashSet<>();

    public void addProduct(Product product){
        favoriteProducts.add(product);
    }

    public void removeProduct(Product product) {
        favoriteProducts.remove(product);
    }
}
