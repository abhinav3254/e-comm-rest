package e_comm.service;


import e_comm.jwt.JwtFilter;
import e_comm.modal.Product;
import e_comm.modal.SaveForLater;
import e_comm.modal.User;
import e_comm.repo.ProductRepository;
import e_comm.repo.SaveForLaterRepository;
import e_comm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaveForLaterService {

    @Autowired
    private SaveForLaterRepository saveForLaterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<String> addInSaveForLater(Long productId) {
        try {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                User user = userRepository.findUserByEmail(jwtFilter.getCurrentUser());

                Optional<SaveForLater> saveForLaterOptional = saveForLaterRepository.findByUser(user);
                SaveForLater saveForLater = null;
                if (saveForLaterOptional.isPresent()) {
                    saveForLater = saveForLaterOptional.get();

                    List<Product> list = saveForLater.getProducts();
                    list.add(list.size(),product);
                    saveForLater.setProducts(list);

                } else {
                    saveForLater = new SaveForLater();
                    List<Product> list = new ArrayList<>();
                    list.add(product);
                    saveForLater.setProducts(list);
                    saveForLater.setUser(user);
                }

                saveForLaterRepository.save(saveForLater);
                return new ResponseEntity<>("added to save for later",HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<SaveForLater> getAllSaveForLater() {
        try {
            User user = userRepository.findUserByEmail(jwtFilter.getCurrentUser());
            Optional<SaveForLater> saveForLater = saveForLaterRepository.findByUser(user);

            return saveForLater.map(forLater -> new ResponseEntity<>(forLater, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteFromSaveForLater(Long productId) {
        try {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {

                User user = userRepository.findUserByEmail(jwtFilter.getCurrentUser());

                Optional<SaveForLater> saveForLaterOptional = saveForLaterRepository.findByUser(user);

                if (saveForLaterOptional.isPresent()) {

                    List<Product> productList = saveForLaterOptional.get().getProducts();

                    // Removes the product with matching ID
                    productList.removeIf(product -> Objects.equals(product.getId(), productId));

                    saveForLaterOptional.get().setProducts(productList);
                    saveForLaterRepository.save(saveForLaterOptional.get());
                    return new ResponseEntity<>("removed product",HttpStatus.OK);

                } return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);

            } else {
                return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteSaveForLater() {
        try {
            User user = userRepository.findUserByEmail(jwtFilter.getCurrentUser());

            Optional<SaveForLater> saveForLaterOptional = saveForLaterRepository.findByUser(user);

            if (saveForLaterOptional.isPresent()) {
                saveForLaterRepository.deleteById(saveForLaterOptional.get().getId());
                return new ResponseEntity<>("deleted",HttpStatus.OK);
            } else {
                return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
