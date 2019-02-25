package application.sisters.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@SequenceGenerator(name = "default_gen", sequenceName = "user_id_seq", allocationSize = 1)
public class User extends BasicEntity{
    String name;
}
