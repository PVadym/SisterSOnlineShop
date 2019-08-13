package application.sisters.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "PRODUCTS_ID_GENERATOR", sequenceName = "PRODUCTS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTS_ID_GENERATOR")
    private Long id;

    private String name;

    private String description;

    private String picture;
//    @Transient
    private Double price;
//    @Transient
    @Enumerated(value = EnumType.STRING)
    private Size size;
    @Transient
    private Brand brand;
}
