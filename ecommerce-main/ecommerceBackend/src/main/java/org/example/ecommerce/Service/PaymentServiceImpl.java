package org.example.ecommerce.Service;

import org.example.ecommerce.Entity.Orders;
import org.example.ecommerce.Entity.Payment;
import org.example.ecommerce.Repository.OrdersRepository;
import org.example.ecommerce.Repository.PaymentRepository;
import org.example.ecommerce.Service.ServiceImpl.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deleteAllPayment() {
        paymentRepository.deleteAll();
    }

    @Override
    public void deletePaymentById(Long id) {
            paymentRepository.deleteById(id);
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }


    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public Payment assignPaymentToOrders(Long paymentId, Long ordersId) {
        Payment payment=paymentRepository.findById(paymentId).orElseThrow(
                ()->new RuntimeException("payment not found")
        );
        Orders orders=ordersRepository.findById(ordersId).orElseThrow(
                ()->new RuntimeException("orders not found")
        );

        payment.setOrder(orders);
        orders.setPayment(payment);

        return paymentRepository.save(payment);
    }

    @Override
    public Boolean exitById(Long id) {
        return paymentRepository.existsById(id);
    }
}
