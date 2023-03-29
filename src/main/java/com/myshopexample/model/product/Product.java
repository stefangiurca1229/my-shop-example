package com.myshopexample.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshopexample.model.department.Department;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String thumbnail;
    @Enumerated(EnumType.STRING)
    private Category category;
    public Product() {
    }

    @ManyToMany
    @JoinTable(
            name = "department_products",
            joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id")
    )
    private List<Department> departments;

    public Product(String title, String description, String thumbnail, Category categorie) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.category = categorie;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", categorie='" + category + '\'' +
                '}';
    }
}
