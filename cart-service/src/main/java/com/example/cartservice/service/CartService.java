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
        if (cartItem.getProductPrice() != null && cartItem.getQuantity() != null) {
            cartItem.setTotalPrice(cartItem.getProductPrice() * cartItem.getQuantity());
        }
        return cartRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
        return cartRepository.findById(id).map(cartItem -> {
            cartItem.setUserId(updatedCartItem.getUserId());
            cartItem.setProductId(updatedCartItem.getProductId());
            cartItem.setProductName(updatedCartItem.getProductName());
            cartItem.setProductPrice(updatedCartItem.getProductPrice());
            cartItem.setProductImageUrl(updatedCartItem.getProductImageUrl());
            cartItem.setQuantity(updatedCartItem.getQuantity());
            cartItem.setSelectedSize(updatedCartItem.getSelectedSize());
            cartItem.setSelectedColor(updatedCartItem.getSelectedColor());
            cartItem.setStatus(updatedCartItem.getStatus());

            if (updatedCartItem.getProductPrice() != null && updatedCartItem.getQuantity() != null) {
                cartItem.setTotalPrice(updatedCartItem.getProductPrice() * updatedCartItem.getQuantity());
            } else {
                cartItem.setTotalPrice(updatedCartItem.getTotalPrice());
            }

            return cartRepository.save(cartItem);
        }).orElseThrow(() -> new RuntimeException("Cart item not found with id " + id));
    }

    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }
}