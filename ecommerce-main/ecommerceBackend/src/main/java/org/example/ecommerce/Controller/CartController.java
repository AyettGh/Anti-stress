package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.Cart;
import org.example.ecommerce.Entity.CartItem;
import org.example.ecommerce.Service.ServiceImpl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/allcart")
    public List<Cart> getALlCart()
    {
        return cartService.getAll();
    }

    @GetMapping("/getcart/{id}")
    public Cart getCartById(@PathVariable Long id)
    {
        return cartService.getCartById(id).orElseThrow(
                ()-> new EntityNotFoundException("no cart is found")
        );
    }

    @PostMapping("/addcart")
    public Cart addCart(@RequestBody Cart cart)
    {
        return cartService.addCart(cart);
    }

    @DeleteMapping("/deletecarts")
    public void deleteCart()
    {
        cartService.deleteAllCart();

    }
    @DeleteMapping("/deletecart/{id}")
    public ResponseEntity<?> deleteCartById(@RequestBody Cart cart, @PathVariable Long id)
    {
        if (cartService.exitById(id))
        {
            cartService.deleteCartById(id);
            HashMap<Cart,String> map=new HashMap<>();
            map.put(cart," this cart is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("cart"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updatecart/{id}")
    public ResponseEntity<?> updateCart(@RequestBody Cart cart,@PathVariable Long id)
    {
        if(cartService.exitById(id))
        {
            Cart cart1=cartService.getCartById(id).orElseThrow(
                    ()->new EntityNotFoundException("cart not found")
            );
            cart1.setCustomer(cart.getCustomer());
            cart1.setItems(cart.getItems());
            cart1.setTotalPrice(cart.getTotalPrice());
            return ResponseEntity.ok().body(cart1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("cart"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PutMapping("/assignCartToCustomer/{idcart}/{idcustomer}")
    public ResponseEntity<Cart>assignCartToCustomer(@PathVariable Long idcart,@PathVariable Long idcustomer)
    {
        Cart cart =cartService.assignCartToCustomer(idcart, idcustomer);
        return ResponseEntity.ok(cart);
    }
}
