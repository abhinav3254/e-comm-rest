package e_comm.service;

import e_comm.jwt.JwtFilter;
import e_comm.modal.Product;
import e_comm.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JwtFilter jwtFilter;

    public ResponseEntity<String> addProduct(Product product) {
        try {
            if (jwtFilter.isAdmin()) {
                product.setCreatedDate(new Date());
                productRepository.save(product);
                return new ResponseEntity<>("added",HttpStatus.CREATED);
            } return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            return new ResponseEntity<>(products,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> updateProduct(Long id, Product product) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> product1 = productRepository.findById(id);

                if (product1.isEmpty()) {
                    return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
                } else {
                    product.setId(product1.get().getId());
                    productRepository.save(product);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            } return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> productOptional = productRepository.findById(id);
                if (productOptional.isPresent()) {
                    productRepository.deleteById(id);
                    return new ResponseEntity<>("product deleted",HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
                }
            } return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
