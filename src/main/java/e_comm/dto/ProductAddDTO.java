package e_comm.dto;


import lombok.Data;

@Data
public class ProductAddDTO {

    private String name;
    private Double price;
    private String img;

    private String brandName;

    private String description;

    private Long category;

    private double weight;
    private double height;
    private Integer quantity;

}
