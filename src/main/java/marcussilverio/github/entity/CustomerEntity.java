package marcussilverio.github.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcussilverio.github.dto.CustomerDto;

import javax.persistence.*;

@Entity
@Table(name ="customer")
@Data
@NoArgsConstructor
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String phone;
  private String email;
  private String address;
  private Long age;

  public CustomerEntity (CustomerDto dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.phone = dto.getPhone();
    this.email = dto.getEmail();
    this.address = dto.getAddress();
    this.age = dto.getAge();
  }
}
