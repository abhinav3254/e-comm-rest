package e_comm.repo;

import e_comm.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(nativeQuery = true, value = "select * from product where brandName like %:query% or name like %:query% or price = :query or description like %:query%;")
    List<Product> findForSearch(@Param("query") String query);


}
