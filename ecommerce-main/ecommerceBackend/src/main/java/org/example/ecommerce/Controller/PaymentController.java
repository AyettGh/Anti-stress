package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.Payment;
import org.example.ecommerce.Service.ServiceImpl.OrdersService;
import org.example.ecommerce.Service.ServiceImpl.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/allpayment")
    public List<Payment> getALlPayment()
    {
        return paymentService.getAll();
    }

    @GetMapping("/getpayment/{id}")
    public Payment getPaymentById(@PathVariable Long id)
    {
        return paymentService.getPaymentById(id).orElseThrow(
                ()-> new EntityNotFoundException("no payment is found")
        );
    }

    @PostMapping("/addpayment")
    public Payment addPayment(@RequestBody Payment payment)
    {
        return paymentService.addPayment(payment);
    }

    @DeleteMapping("/deletepayments")
    public void deletePayment()
    {
        paymentService.deleteAllPayment();

    }
    @DeleteMapping("/deletepayment/{id}")
    public ResponseEntity<?> deletePaymentById(@RequestBody Payment payment, @PathVariable Long id)
    {
        if (paymentService.exitById(id))
        {
            paymentService.deletePaymentById(id);
            HashMap<Payment,String> map=new HashMap<>();
            map.put(payment," this payment is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("payment"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updatepayment/{id}")
    public ResponseEntity<?> updatePayment(@RequestBody Payment payment,@PathVariable Long id)
    {
        if(paymentService.exitById(id))
        {
            Payment payment1=paymentService.getPaymentById(id).orElseThrow(
                    ()->new EntityNotFoundException("payment not found")
            );
            payment1.setPaymentDate(payment.getPaymentDate());
            payment1.setPaymentMethod(payment.getPaymentMethod());
            payment1.setPaymentStatus(payment.getPaymentStatus());
            payment1.setOrder(payment.getOrder());
            return ResponseEntity.ok().body(payment1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("payment"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PutMapping("/assignPaymentToOrders/{paymentId}/{ordersId}")
    public ResponseEntity<Payment>assignPaymentToOrders(@PathVariable Long paymentId,@PathVariable Long ordersId)
    {
        Payment payment=paymentService.assignPaymentToOrders(paymentId, ordersId);
        return ResponseEntity.ok(payment);
    }


}
