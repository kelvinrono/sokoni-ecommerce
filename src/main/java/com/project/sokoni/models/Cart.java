package com.project.sokoni.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
@Data
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long cartId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id")
    private User user;


    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cart_id"
    )
    private List<CartItem> cartItems;

//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "cart_id",
//            referencedColumnName = "cart_id"
//    )
//    private List<Product> cart;


}
