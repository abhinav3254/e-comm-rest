package e_comm.service;


import e_comm.jwt.JwtFilter;
import e_comm.modal.CartItem;
import e_comm.modal.Product;
import e_comm.modal.User;
import e_comm.repo.CartItemRepository;
import e_comm.repo.ProductRepository;
import e_comm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtFilter jwtFilter;

    public ResponseEntity<String> addToCartItem(Long productId) {
        try {

            CartItem cartItem = new CartItem();
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                User user = userRepository.findUserByEmail(jwtFilter.getCurrentUser());

                cartItem.setAddedDate(new Date());
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(1);

                cartItemRepository.save(cartItem);

                return new ResponseEntity<>("Added to cart",HttpStatus.OK);

            } return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<CartItem>> getAllCartItem() {
        try {

            List<CartItem> list = cartItemRepository.findByUser(jwtFilter.getLoggedInUser());
            return new ResponseEntity<>(list,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> incrementQuantity(Long productId) {
        try {

            Optional<Product> productOptional = productRepository.findById(productId);
            if(productOptional.isPresent()) {

                CartItem cartItem = cartItemRepository.findByProductAndUser(productOptional.get(),jwtFilter.getLoggedInUser());

                cartItem.setQuantity(cartItem.getQuantity()+1);

                cartItemRepository.save(cartItem);

                return new ResponseEntity<>("incremented",HttpStatus.OK);

            } else {
                return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> decrementQuantity(Long productId) {
        try {

            Optional<Product> productOptional = productRepository.findById(productId);
            if(productOptional.isPresent()) {

                CartItem cartItem = cartItemRepository.findByProductAndUser(productOptional.get(),jwtFilter.getLoggedInUser());

                cartItem.setQuantity(cartItem.getQuantity()-1);

                if (cartItem.getQuantity()>0) {
                    cartItemRepository.save(cartItem);
                } else {
                    cartItemRepository.deleteById(cartItem.getId());
                    return new ResponseEntity<>("Item removed from cart",HttpStatus.OK);
                }

                return new ResponseEntity<>("decremented",HttpStatus.OK);

            } else {
                return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteOneCartItem(Long cartItemId) {
        try {

            cartItemRepository.deleteById(cartItemId);
            return new ResponseEntity<>("Deleted Item",HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
