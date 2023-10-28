package e_comm.repo;

import e_comm.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM USER WHERE email=:email")
    User findUserByEmail(@Param("email")String email);

}