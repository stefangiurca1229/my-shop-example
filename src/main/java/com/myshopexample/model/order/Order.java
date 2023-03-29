package com.myshopexample.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshopexample.model.customer.Customer;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    @Valid
    private Set<OrderProduct> orderProducts = new HashSet<>();
}
