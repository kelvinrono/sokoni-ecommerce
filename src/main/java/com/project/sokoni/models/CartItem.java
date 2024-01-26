package com.project.sokoni.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
@Data
public class CartItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
