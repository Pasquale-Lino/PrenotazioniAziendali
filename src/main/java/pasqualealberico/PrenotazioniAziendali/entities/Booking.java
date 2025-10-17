package pasqualealberico.PrenotazioniAziendali.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "bookings") //uniqueConstraints
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne // unidirezionale Booking -> Travel
    @JoinColumn(name = "travel_id", nullable = false)
    private Travel travel;

    @ManyToOne //unidirezionale booking > TRavel
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate bookingDate; //data del viaggio o data richiesta
    private String notes; //preferenze
    private LocalDateTime requestedAt;
}
