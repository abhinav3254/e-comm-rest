package e_comm.modal;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SaveForLater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    List<Product> products;

}
