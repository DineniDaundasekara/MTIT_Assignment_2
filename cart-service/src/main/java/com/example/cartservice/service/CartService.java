package com.example.cartservice.service;

import com.example.cartservice.entity.CartItem;
import com.example.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartRepository.findAll();
    }

    public Optional<CartItem> getCartItemById(Long id) {
        return cartRepository.findById(id);
    }

    public CartItem saveCartItem(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
        return cartRepository.findById(id).map(cartItem -> {
            cartItem.setUserId(updatedCartItem.getUserId());
            cartItem.setProductId(updatedCartItem.getProductId());
            cartItem.setQuantity(updatedCartItem.getQuantity());
            return cartRepository.save(cartItem);
        }).orElseThrow(() -> new RuntimeException("Cart item not found with id " + id));
    }

    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }
}