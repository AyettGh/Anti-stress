package org.example.ecommerce.Service.ServiceImpl;

import org.example.ecommerce.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAll();
    public Product addProduct(Product product);
    public void  deleteAllProduct();
    public void deleteProductById(Long id);
    public Boolean exitById(Long id);
    public Optional<Product> getProductById(Long id);
}
