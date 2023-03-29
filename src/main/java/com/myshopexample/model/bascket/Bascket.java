package com.myshopexample.model.bascket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshopexample.model.customer.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Bascket implements Serializable{
    @Id
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "bascket")
    private Set<BascketProduct> bascketProducts = new HashSet<>();
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @MapsId
    private Customer customer;
    public void removeBascketProduct(BascketProduct bascketProduct){
        bascketProducts.remove(bascketProduct);
    }
    public void removeAllBascketProducts() {
        bascketProducts.clear();
    }
}
