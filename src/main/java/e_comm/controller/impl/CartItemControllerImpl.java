package e_comm.controller.impl;

import e_comm.controller.CartItemController;
import e_comm.modal.CartItem;
import e_comm.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CartItemControllerImpl implements CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Override
    public ResponseEntity<String> addToCartItem(Long productId) {
        return cartItemService.addToCartItem(productId);
    }

    @Override
    public ResponseEntity<List<CartItem>> getAllCartItem() {
        return cartItemService.getAllCartItem();
    }

    @Override
    public ResponseEntity<String> updateQuantity(Integer quantity) {
        return cartItemService.updateQuantity(quantity);
    }

    @Override
    public ResponseEntity<String> deleteCartItem(Long cartItemId) {
        return cartItemService.deleteCartItem(cartItemId);
    }
}
