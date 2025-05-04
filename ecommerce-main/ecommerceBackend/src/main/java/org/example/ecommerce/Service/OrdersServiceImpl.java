package org.example.ecommerce.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.*;
import org.example.ecommerce.Repository.CartRepository;
import org.example.ecommerce.Repository.CustomerRepository;
import org.example.ecommerce.Repository.OrdersRepository;
import org.example.ecommerce.Repository.PaymentRepository;
import org.example.ecommerce.Service.ServiceImpl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders addOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void deleteAllOrders() {
            ordersRepository.deleteAll();
    }

    @Override
    public void deleteOrdersById(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public Boolean exitById(Long id) {
        return ordersRepository.existsById(id);
    }

    @Override
    public Optional<Orders> getOrdersById(Long id) {
        return ordersRepository.findById(id);
    }

@Autowired
private CustomerRepository customerRepository;
    @Override
    public Orders assignOrdersToCustomer(Long ordersId, Long customerId ) {
        Orders orders=ordersRepository.findById(ordersId).orElseThrow(
                ()->new RuntimeException("orders not found")
        );
        Customer customer =customerRepository.findById(customerId).orElseThrow(
                ()->new RuntimeException("customer not found")
        );
        orders.setCustomer(customer);
        return ordersRepository.save(orders);
    }

}
