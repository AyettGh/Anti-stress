package org.example.ecommerce.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.Customer;
import org.example.ecommerce.Repository.CartRepository;
import org.example.ecommerce.Repository.CustomerRepository;
import org.example.ecommerce.Service.ServiceImpl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteAllCustomer() {
        customerRepository.deleteAll();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }


    @Override
    public Boolean exitById(Long id) {
        return customerRepository.existsById(id);
    }
}
