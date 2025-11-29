package com.example.store.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " ["+
                "id=" + id + ","+
                "name=" + name + ","+
                "description=" + description + ","+
                "price=" + price+"]";
    }

}