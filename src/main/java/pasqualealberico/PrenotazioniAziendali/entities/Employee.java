package pasqualealberico.PrenotazioniAziendali.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String name;
    private String cognome;
    private String email;

    private String avatar; //avatar url
}
