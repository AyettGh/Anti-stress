package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.CartItem;
import org.example.ecommerce.Entity.Orders;
import org.example.ecommerce.Entity.Payment;
import org.example.ecommerce.Service.ServiceImpl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/allorders")
    public List<Orders> getALlOrders()
    {
        return ordersService.getAll();
    }

    @GetMapping("/getorders/{id}")
    public Orders getOrdersById(@PathVariable Long id)
    {
        return ordersService.getOrdersById(id).orElseThrow(
                ()-> new EntityNotFoundException("no orders is found")
        );
    }

    @PostMapping("/addorders")
    public Orders addOrders(@RequestBody Orders orders)
    {
        return ordersService.addOrders(orders);
    }

    @DeleteMapping("/deleteorderss")
    public void deleteOrders()
    {
        ordersService.deleteAllOrders();

    }
    @DeleteMapping("/deleteorders/{id}")
    public ResponseEntity<?> deleteOrdersById(@RequestBody Orders orders, @PathVariable Long id)
    {
        if (ordersService.exitById(id))
        {
            ordersService.deleteOrdersById(id);
            HashMap<Orders,String> map=new HashMap<>();
            map.put(orders," this orders is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("orders"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updateorders/{id}")
    public ResponseEntity<?> updateOrders(@RequestBody Orders orders,@PathVariable Long id)
    {
        if(ordersService.exitById(id))
        {
            Orders orders1=ordersService.getOrdersById(id).orElseThrow(
                    ()->new EntityNotFoundException("orders not found")
            );
            orders1.setOrderDate(orders.getOrderDate());
            orders1.setCustomer(orders.getCustomer());
            orders1.setItems(orders.getItems());
            orders1.setPayment(orders.getPayment());
            orders1.setCustomer(orders.getCustomer());
            orders1.setStatus(orders.getStatus());
            orders1.setTotalAmount(orders.getTotalAmount());
            return ResponseEntity.ok().body(orders1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("orders"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PutMapping("/assignOrdersToCustomer/{ordersId}/{customerId}")
    public ResponseEntity<Orders> assignOrdersToCustomer(@PathVariable Long ordersId,@PathVariable Long customerId)
    {
        Orders orders=ordersService.assignOrdersToCustomer(ordersId, customerId);
        return ResponseEntity.ok(orders);
    }
}
