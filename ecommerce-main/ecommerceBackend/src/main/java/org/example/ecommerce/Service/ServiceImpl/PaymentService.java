package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    public List<Payment> getAll();
    public Payment addPayment(Payment payment);
    public void  deleteAllPayment();
    public void deletePaymentById(Long id);
    public Boolean exitById(Long id);
    public Optional<Payment> getPaymentById(Long id);
    public Payment assignPaymentToOrders(Long paymentId,Long ordersId);
}
