package e_comm.controller.impl;

import e_comm.controller.CategoryController;
import e_comm.modal.Category;
import e_comm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CategoryControllerImpl implements CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<String> addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    @Override
    public ResponseEntity<List<Category>> getCategory() {
        return categoryService.getCategory();
    }

    @Override
    public ResponseEntity<String> updateCategory(Long id, Category category) {
        return categoryService.updateCategory(id,category);
    }

    @Override
    public ResponseEntity<String> deleteCategory(Long id) {
        return categoryService.deleteCategory(id);
    }
}
