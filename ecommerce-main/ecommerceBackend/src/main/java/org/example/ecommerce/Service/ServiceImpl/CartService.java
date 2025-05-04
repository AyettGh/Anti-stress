package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public List<Cart> getAll();
    public Cart addCart(Cart cart);
    public void  deleteAllCart();
    public void deleteCartById(Long id);
    public Boolean exitById(Long id);
    public Optional<Cart> getCartById(Long id);
    public Cart assignCartToCustomer(Long cartId, Long customerId);
}
