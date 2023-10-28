package e_comm.repo;

import e_comm.modal.SaveForLater;
import e_comm.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SaveForLaterRepository extends JpaRepository<SaveForLater,Long> {

    Optional<SaveForLater> findByUser(User user);

}
