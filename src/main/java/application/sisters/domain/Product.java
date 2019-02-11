package application.sisters.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@Entity
public class Product extends BasicEntity {
    private String name;
    private Double price;
    @Enumerated(value = EnumType.STRING)
    private Size size;
}
