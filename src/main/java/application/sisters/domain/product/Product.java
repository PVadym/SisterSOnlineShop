package application.sisters.domain.product;

import application.sisters.domain.BasicEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "product_id_seq", allocationSize = 1)
public class Product extends BasicEntity {

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
