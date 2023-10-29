package e_comm.modal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @OneToOne
    private User user;

    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addedDate;

}
