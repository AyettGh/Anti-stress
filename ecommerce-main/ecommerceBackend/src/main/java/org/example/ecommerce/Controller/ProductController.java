package org.example.ecommerce.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.Product;
import org.example.ecommerce.Repository.ProductRepository;
import org.example.ecommerce.Service.ServiceImpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ecommerce")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/allproduct")
    public List<Product> getALlProduct()
    {
        return productService.getAll();
    }

    @GetMapping("/getproduct/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id).orElseThrow(
                ()-> new EntityNotFoundException("no product is found")
        );
    }

//    @PostMapping("/addproduct")
//    public Product addProduct(@RequestBody Product product)
//    {
//        return productService.addProduct(product);
//    }

    @PostMapping("/test")
    public ResponseEntity<?> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("stock") Integer stock,
            @RequestParam("image") MultipartFile imageFile) {

        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            product.setImage(imageFile.getBytes()); // Stocke le contenu du fichier dans le champ byte[]

            Product saved = productRepository.save(product);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la lecture de l'image.");
        }
    }

    @DeleteMapping("/deleteproducts")
    public void deleteproduct()
    {
        productService.deleteAllProduct();

    }
//    @DeleteMapping("/deleteproduct/{id}")
//    public ResponseEntity<?> deleteProductById(@RequestBody Product product,@PathVariable Long id)
//    {
//        if (productService.exitById(id))
//        {
//            productService.deleteProductById(id);
//            HashMap<Product,String> map=new HashMap<>();
//            map.put(product," this product is deleted");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
//        }
//        else {
//            HashMap<String,String> map=new HashMap<>();
//            map.put("product"," not found");
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
//        }
//
//    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        if (productService.exitById(id)) {
            productService.deleteProductById(id);
            Map<String, String> map = new HashMap<>();
            map.put("message", "Product deleted successfully");
            return ResponseEntity.ok(map);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("error", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }


    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable Long id)
    {
        if(productService.exitById(id))
        {
            Product product1=productService.getProductById(id).orElseThrow(
                    ()->new EntityNotFoundException("product not found")
            );
            product1.setName(product.getName());
            product1.setDescription(product.getDescription());
//            product1.setCategory(product.getCategory());
            product1.setPrice(product.getPrice());
            product1.setCartItems(product.getCartItems());
            product1.setStock(product.getStock());
            productService.addProduct(product1);
            return ResponseEntity.ok().body(product1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("product"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProductWithImage(@RequestPart("product") String productJson,
                                                 @RequestPart("image") MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);
            product.setImage(imageFile.getBytes());

            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }

    @GetMapping("/productimage/{id}")
    public ResponseEntity<?> getProductImage(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            byte[] imageBytes = optionalProduct.get().getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return ResponseEntity.ok().body(base64Image);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

}