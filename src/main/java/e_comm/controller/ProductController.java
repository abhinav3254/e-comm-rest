package e_comm.controller;


import e_comm.dto.ProductAddDTO;
import e_comm.modal.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public interface ProductController {

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductAddDTO product);

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts();

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,@RequestBody Product product);

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id);

    @PostMapping("/search/{query}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String query);

}
