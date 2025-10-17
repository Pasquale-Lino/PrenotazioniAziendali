package pasqualealberico.PrenotazioniAziendali.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pasqualealberico.PrenotazioniAziendali.entities.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    //cerco prenotazioni per dipendente in una data
    boolean existsByEmployeeIdAndBookingDate(UUID employeeId, LocalDate bookingDate);
    List<Booking> findByEmployeeId(UUID employeeId);
    List<Booking> findByTravelId(UUID travelId);
}
