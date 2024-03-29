package e_comm.controller.impl;

import e_comm.controller.ProductController;
import e_comm.dto.ProductAddDTO;
import e_comm.modal.Product;
import e_comm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<String> addProduct(ProductAddDTO product) {
        return productService.addProduct(product);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public ResponseEntity<String> updateProduct(Long id, Product product) {
        return productService.updateProduct(id,product);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        return productService.deleteProduct(id);
    }

    @Override
    public ResponseEntity<List<Product>> searchProduct(String query) {
        return productService.searchProduct(query);
    }
}
