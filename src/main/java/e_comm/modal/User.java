package e_comm.modal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private String city;
    private String state;
    private String zipcode;

    private String role;
    private boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
}
