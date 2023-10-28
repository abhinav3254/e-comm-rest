package e_comm.modal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String img;

    @Column(length = 2000)
    private String description;

    @OneToOne
    private Category category;

    private double weight;
    private double height;
    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

}
