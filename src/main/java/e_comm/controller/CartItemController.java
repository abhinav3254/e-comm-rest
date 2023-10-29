package e_comm.controller;


import e_comm.modal.CartItem;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart-item")
public interface CartItemController {

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addToCartItem(@PathVariable("productId") Long productId);

    @GetMapping("/get")
    public ResponseEntity<List<CartItem>> getAllCartItem();

    @PostMapping("/update/{quantity}")
    public ResponseEntity<String> updateQuantity(@Param("quantity") Integer quantity);

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@Param("cartItemId") Long cartItemId);


}
