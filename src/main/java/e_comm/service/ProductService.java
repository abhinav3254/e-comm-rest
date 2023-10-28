package e_comm.service;

import e_comm.dto.ProductAddDTO;
import e_comm.jwt.JwtFilter;
import e_comm.modal.Category;
import e_comm.modal.Product;
import e_comm.repo.CategoryRepository;
import e_comm.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JwtFilter jwtFilter;

    public ResponseEntity<String> addProduct(ProductAddDTO productAddDTO) {
        try {
            if (jwtFilter.isAdmin()) {

                Optional<Category> categoryOptional = categoryRepository.findById(productAddDTO.getCategory());

                if (categoryOptional.isPresent()) {
                    Product product = getProduct(productAddDTO, categoryOptional);
                    productRepository.save(product);
                    return new ResponseEntity<>("added",HttpStatus.CREATED);
                } else return new ResponseEntity<>("product can't be empty",HttpStatus.BAD_REQUEST);
            } return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Product getProduct(ProductAddDTO productAddDTO, Optional<Category> categoryOptional) {
        Product product = new Product();

        product.setName(productAddDTO.getName());
        product.setPrice(productAddDTO.getPrice());
        product.setImg(productAddDTO.getImg());
        product.setBrandName(productAddDTO.getBrandName());
        product.setDescription(productAddDTO.getDescription());
        product.setWeight(productAddDTO.getWeight());
        product.setHeight(productAddDTO.getHeight());
        product.setQuantity(productAddDTO.getQuantity());

        product.setCategory(categoryOptional.get());

        product.setCreatedDate(new Date());
        return product;
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
                Optional<Product> existingProduct = productRepository.findById(id);

                if (existingProduct.isEmpty()) {
                    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
                } else {
                    Product productToUpdate = existingProduct.get();

                    // Update the fields with new values if they are not null and not empty
                    if (product.getName() != null && !product.getName().isEmpty()) {
                        productToUpdate.setName(product.getName());
                    }
                    if (product.getPrice() != null) {
                        productToUpdate.setPrice(product.getPrice());
                    }
                    if (product.getImg() != null && !product.getImg().isEmpty()) {
                        productToUpdate.setImg(product.getImg());
                    }
                    if (product.getBrandName() != null && !product.getBrandName().isEmpty()) {
                        productToUpdate.setBrandName(product.getBrandName());
                    }
                    if (product.getDescription() != null && !product.getDescription().isEmpty()) {
                        productToUpdate.setDescription(product.getDescription());
                    }
                    if (product.getCategory() != null) {
                        productToUpdate.setCategory(product.getCategory());
                    }
                    if (product.getWeight() != 0.0) {
                        productToUpdate.setWeight(product.getWeight());
                    }
                    if (product.getHeight() != 0.0) {
                        productToUpdate.setHeight(product.getHeight());
                    }

                    if (product.getQuantity() != null) {
                        productToUpdate.setQuantity(product.getQuantity());
                    }

                    // Save the updated product
                    Product updatedProduct = productRepository.save(productToUpdate);

                    if (updatedProduct != null) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Failed to update the product", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Handle any exceptions that might occur during the update
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
