package org.example.ecommerce.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommerce.Entity.CartItem;
import org.example.ecommerce.Service.ServiceImpl.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ecommerce")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/allcartItem")
    public List<CartItem> getALlCartItem()
    {
        return cartItemService.getAll();
    }

    @GetMapping("/getcartItem/{id}")
    public CartItem getCartItemById(@PathVariable Long id)
    {
        return cartItemService.getCartItemById(id).orElseThrow(
                ()-> new EntityNotFoundException("no cartItem is found")
        );
    }

    @PostMapping("/addcartItem")
    public CartItem addCartItem(@RequestBody CartItem cartItem)
    {
        return cartItemService.addCartItem(cartItem);
    }

    @DeleteMapping("/deletecartItems")
    public void deleteCartItem()
    {
        cartItemService.deleteAllCartItem();

    }
    @DeleteMapping("/deletecartItem/{id}")
    public ResponseEntity<?> deleteCartItemById(@RequestBody CartItem cartItem, @PathVariable Long id)
    {
        if (cartItemService.exitById(id))
        {
            cartItemService.deleteCartItemById(id);
            HashMap<CartItem,String> map=new HashMap<>();
            map.put(cartItem," this cartItem is deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("cartItem"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

    }

    @PutMapping("/updatecartItem/{id}")
    public ResponseEntity<?> updateCart(@RequestBody CartItem cartItem,@PathVariable Long id)
    {
        if(cartItemService.exitById(id))
        {
            CartItem cartItem1=cartItemService.getCartItemById(id).orElseThrow(
                    ()->new EntityNotFoundException("cartItem not found")
            );
            cartItem1.setOrder(cartItem.getOrder());
            cartItem1.setProduct(cartItem.getProduct());
            cartItem1.setCart(cartItem.getCart());
            cartItem1.setQuantity(cartItem.getQuantity());
            cartItem1.setSubTotal(cartItem.getSubTotal());
            return ResponseEntity.ok().body(cartItem1);
        }
        else {
            HashMap<String,String> map=new HashMap<>();
            map.put("cartItem"," not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }
    @PutMapping("/assignCartItemToProduct/{cartItemId}/{productId}")
    public ResponseEntity<CartItem>assignCartItemToProduct(@PathVariable Long cartItemId,@PathVariable Long productId)
    {
        CartItem cartItem=cartItemService.assignCartItemToProduct(cartItemId,productId);
        return ResponseEntity.ok(cartItem);
    }
    @PutMapping("/assignCartItemToOrders/{cartItemId}/{ordersId}")
    public  ResponseEntity<CartItem>assignCartItemToOrders(@PathVariable Long cartItemId,@PathVariable Long ordersId)
    {
        CartItem cartItem=cartItemService.assignCartItemToOrders(cartItemId, ordersId);
        return ResponseEntity.ok(cartItem);
    }
    @PutMapping("/assignCartItemToCart/{cartItemId}/{cartId}")
    public  ResponseEntity<CartItem>assignCartItemToCart(@PathVariable Long cartItemId,@PathVariable Long cartId)
    {
        CartItem cartItem=cartItemService.assignCartItemToCart(cartItemId, cartId);
        return ResponseEntity.ok(cartItem);
    }


}

