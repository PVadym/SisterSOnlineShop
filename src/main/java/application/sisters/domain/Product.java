package application.sisters.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product extends BasicEntity {
    private String name;
    private Double price;
    private Size size;
}
