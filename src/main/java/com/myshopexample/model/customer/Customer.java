package com.myshopexample.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshopexample.model.FavoriteList;
import com.myshopexample.model.bascket.Bascket;
import com.myshopexample.model.order.Order;
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
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private Bascket bascket;

    @OneToOne(mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private FavoriteList favoriteList;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders =  new HashSet<>();

    public Customer(){}

    public Customer(String name, String password, String phone, Role role) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
}