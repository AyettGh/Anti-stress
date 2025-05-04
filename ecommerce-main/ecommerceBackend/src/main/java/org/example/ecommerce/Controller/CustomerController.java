package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.Customer;
import org.example.ecommerce.Entity.Orders;
import org.example.ecommerce.Service.ServiceImpl.CustomerService;
import org.example.ecommerce.Service.ServiceImpl.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/allcustomer")
    public List<Customer> getALlCustomer()
    {
        return customerService.getAll();
    }

    @GetMapping("/getcustomer/{id}")
    public Customer getCustomerById(@PathVariable Long id)
    {
        return customerService.getCustomerById(id).orElseThrow(
                ()-> new EntityNotFoundException("no customer is found")
        );
    }

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody Customer customer)
    {
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("/deletecustomers")
    public void deleteCustomer()
    {
        customerService.deleteAllCustomer();

    }
    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<?> deleteCustomerById(@RequestBody Customer customer, @PathVariable Long id)
    {
        if (customerService.exitById(id))
        {
            customerService.deleteCustomerById(id);
            HashMap<Customer,String> map=new HashMap<>();
            map.put(customer," this customer is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("customer"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,@PathVariable Long id)
    {
        if(customerService.exitById(id))
        {
            Customer customer1=customerService.getCustomerById(id).orElseThrow(
                    ()->new EntityNotFoundException("customer not found")
            );
            customer1.setName(customer.getName());
            customer1.setEmail(customer.getEmail());
            customer1.setRole(customer.getRole());
            customer1.setAddress(customer.getAddress());
            customer1.setPhoneNumber(customer.getPhoneNumber());
            customer1.setCart(customer.getCart());
            customer1.setOrders(customer.getOrders());
            return ResponseEntity.ok().body(customer1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("customer"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }




}
