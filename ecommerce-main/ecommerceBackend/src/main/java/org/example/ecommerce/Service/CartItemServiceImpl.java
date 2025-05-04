package org.example.ecommerce.Service;

import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.CartItem;
import org.example.ecommerce.Entity.Orders;
import org.example.ecommerce.Entity.Product;
import org.example.ecommerce.Repository.CartItemRepository;
import org.example.ecommerce.Repository.CartRepository;
import org.example.ecommerce.Repository.OrdersRepository;
import org.example.ecommerce.Repository.ProductRepository;
import org.example.ecommerce.Service.ServiceImpl.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteAllCartItem() {
            cartItemRepository.deleteAll();
    }

    @Override
    public void deleteCartItemById(Long id) {
            cartItemRepository.deleteById(id);
    }

    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public CartItem assignCartItemToProduct(Long cartItemId, Long productId) {
        CartItem cartItem =cartItemRepository.findById(cartItemId).orElseThrow(
                ()->new RuntimeException("cartItem not found")
        );
        Product product=productRepository.findById(productId).orElseThrow(
                ()->new RuntimeException("product not found")
        );
        cartItem.setProduct(product);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem assignCartItemToOrders(Long cartItemId, Long ordersId) {
        CartItem cartItem =cartItemRepository.findById(cartItemId).orElseThrow(
                ()->new RuntimeException("cartItem not found")
        );
        Orders orders=ordersRepository.findById(ordersId).orElseThrow(
                ()->new RuntimeException("orders not found")
        );
        cartItem.setOrder(orders);
        return cartItemRepository.save(cartItem);
    }
@Autowired
private CartRepository cartRepository;
    @Override
    public CartItem assignCartItemToCart(Long cartItemId, Long cartId) {
        CartItem cartItem =cartItemRepository.findById(cartItemId).orElseThrow(
                ()->new RuntimeException("cartItem not found")
        );
        Cart cart=cartRepository.findById(cartId).orElseThrow(
                ()->new RuntimeException("cart not found")
        );
        cartItem.setCart(cart);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Boolean exitById(Long id) {
        return cartItemRepository.existsById(id);
    }

}
