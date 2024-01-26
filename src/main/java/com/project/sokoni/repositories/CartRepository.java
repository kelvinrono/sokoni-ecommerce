package com.project.sokoni.repositories;

import com.project.sokoni.models.Cart;
import com.project.sokoni.models.CartItem;
import com.project.sokoni.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{

    Optional<Cart> findByUser(User user);

    Optional<CartItem> findByCartIdAndAndUser(Long cartId, User user);
}
