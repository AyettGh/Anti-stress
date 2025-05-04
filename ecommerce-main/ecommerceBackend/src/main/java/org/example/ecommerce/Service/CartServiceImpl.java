package org.example.ecommerce.Service;

import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.Customer;
import org.example.ecommerce.Repository.CartRepository;
import org.example.ecommerce.Repository.CustomerRepository;
import org.example.ecommerce.Service.ServiceImpl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteAllCart() {
        cartRepository.deleteAll();
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }
    @Override
    public Boolean exitById(Long id) {
        return cartRepository.existsById(id);
    }

    @Autowired
    private CustomerRepository customerRepository;
    public Cart assignCartToCustomer(Long cartId, Long customerId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("cart not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("customer not found"));

        cart.setCustomer(customer);
        customer.setCart(cart);
        return cartRepository.save(cart);
    }

}
