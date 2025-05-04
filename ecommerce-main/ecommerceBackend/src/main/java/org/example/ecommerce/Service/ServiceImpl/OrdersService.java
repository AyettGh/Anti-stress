package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.CartItem;
import org.example.ecommerce.Entity.Orders;
import org.example.ecommerce.Entity.Payment;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    public List<Orders> getAll();
    public Orders addOrders(Orders orders);
    public void  deleteAllOrders();
    public void deleteOrdersById(Long id);
    public Boolean exitById(Long id);
    public Optional<Orders> getOrdersById(Long id);
    public Orders assignOrdersToCustomer(Long customerId, Long ordersId);


}
