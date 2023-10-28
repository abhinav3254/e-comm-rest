package e_comm.controller;


import e_comm.modal.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
public interface CategoryController {

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody Category category);

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategory();

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category);

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id);

}
