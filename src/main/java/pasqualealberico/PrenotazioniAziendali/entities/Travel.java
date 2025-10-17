package pasqualealberico.PrenotazioniAziendali.entities;

import jakarta.persistence.*;
import lombok.*;
import pasqualealberico.PrenotazioniAziendali.enums.TravelStatus;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="Travels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Travel {
    @Id
    @GeneratedValue
    private UUID id;
    private String destination;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TravelStatus status; // IN_PROGRAM, COMPLETED
}
