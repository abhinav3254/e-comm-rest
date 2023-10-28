package e_comm.repo;

import e_comm.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(nativeQuery = true,value = "select * from category where name=:category")
    Category findByCategory(@Param("category")String category);

}
