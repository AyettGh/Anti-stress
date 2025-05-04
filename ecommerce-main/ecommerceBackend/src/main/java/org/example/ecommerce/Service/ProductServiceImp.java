package org.example.ecommerce.Service;

import org.example.ecommerce.Entity.Product;
import org.example.ecommerce.Repository.ProductRepository;
import org.example.ecommerce.Service.ServiceImpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteAllProduct() {
           productRepository.deleteAll();
    }

    @Override
    public void deleteProductById(Long id) {
         productRepository.deleteById(id); ;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public Boolean exitById(Long id) {
        return productRepository.existsById(id);
    }
}
