package e_comm.service;


import e_comm.jwt.JwtFilter;
import e_comm.modal.Category;
import e_comm.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JwtFilter jwtFilter;

    public ResponseEntity<String> addCategory(Category category) {
        try {
            if (jwtFilter.isAdmin()) {
                Category category1 = categoryRepository.findByCategory(category.getName());

                if (Objects.isNull(category1)) {

                    categoryRepository.save(category);

                    return new ResponseEntity<>("added "+category.getName(),HttpStatus.CREATED);

                } else {
                    return new ResponseEntity<>("category already exists",HttpStatus.ALREADY_REPORTED);
                }
            }
            return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Category>> getCategory() {
        try {
            List<Category> list = categoryRepository.findAll();
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> updateCategory(Long id,Category category) {
        try {
            if(jwtFilter.isAdmin()) {

                Optional<Category> categoryOptional = categoryRepository.findById(id);

                if (categoryOptional.isEmpty()) {
                    return new ResponseEntity<>("category not found",HttpStatus.BAD_REQUEST);
                } else {
                    Category category1 = categoryOptional.get();
                    category1.setName(category.getName());
                    categoryRepository.save(category1);
                    return new ResponseEntity<>("category updated",HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteCategory(Long id) {
        try {
            if(jwtFilter.isAdmin()) {
                if(jwtFilter.isAdmin()) {

                    Optional<Category> categoryOptional = categoryRepository.findById(id);

                    if (categoryOptional.isEmpty()) {
                        return new ResponseEntity<>("category not found",HttpStatus.BAD_REQUEST);
                    } else {
                        categoryRepository.deleteById(id);
                        return new ResponseEntity<>("category deleted",HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
