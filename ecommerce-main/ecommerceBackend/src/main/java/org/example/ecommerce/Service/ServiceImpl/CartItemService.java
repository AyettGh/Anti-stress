package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    public List<CartItem> getAll();
    public CartItem addCartItem(CartItem cartItem);
    public void  deleteAllCartItem();
    public void deleteCartItemById(Long id);
    public Boolean exitById(Long id);
    public Optional<CartItem> getCartItemById(Long id);
    public CartItem assignCartItemToProduct(Long cartItemId,Long productId);
    public CartItem assignCartItemToOrders(Long cartItemId,Long ordersId);
    public CartItem assignCartItemToCart(Long cartItemId,Long cartId);

}
