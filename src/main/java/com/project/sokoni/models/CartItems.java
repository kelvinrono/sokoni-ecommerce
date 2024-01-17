package com.project.sokoni.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
@Data
public class CartItems {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
