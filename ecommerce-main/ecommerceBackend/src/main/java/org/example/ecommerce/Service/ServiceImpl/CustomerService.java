package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> getAll();
    public Customer addCustomer(Customer customer);
    public void  deleteAllCustomer();
    public void deleteCustomerById(Long id);
    public Boolean exitById(Long id);
    public Optional<Customer> getCustomerById(Long id);

}
