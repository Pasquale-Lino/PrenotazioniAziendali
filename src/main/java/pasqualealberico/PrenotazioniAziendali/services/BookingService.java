package pasqualealberico.PrenotazioniAziendali.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pasqualealberico.PrenotazioniAziendali.entities.Booking;
import pasqualealberico.PrenotazioniAziendali.entities.Employee;
import pasqualealberico.PrenotazioniAziendali.entities.Travel;
import pasqualealberico.PrenotazioniAziendali.exceptions.BadRequestException;
import pasqualealberico.PrenotazioniAziendali.exceptions.NotFoundException;
import pasqualealberico.PrenotazioniAziendali.payloads.NewBookingPayload;
import pasqualealberico.PrenotazioniAziendali.repositories.BookingRepository;
import pasqualealberico.PrenotazioniAziendali.repositories.EmployeeRepository;
import pasqualealberico.PrenotazioniAziendali.repositories.TravelRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BookingService {

@Autowired private BookingRepository bookingRepository;
@Autowired private TravelRepository travelRepository;
@Autowired private EmployeeRepository employeeRepository;

//POST creo prenotazione
public Booking createBooking(NewBookingPayload payload) {
    UUID travelId = payload.TravelId();
    UUID employeeId= payload.employeeId();
    LocalDate date = payload.bookingDate();

    Travel travel = travelRepository.findById(travelId).orElseThrow(()-> new NotFoundException(travelId));
    Employee emp= employeeRepository.findById(employeeId).orElseThrow(()-> new NotFoundException(employeeId));

    //dipendente non può avere più prenotazioni nello stesso giorno, quindi...
    boolean exists= bookingRepository.existsByEmployeeIdAndBookingDate(employeeId,date);
    if (exists){
        throw new BadRequestException("Dipendente già prenotato per la data"+ date);
    }
    Booking b= new Booking();
    b.setTravel(travel);
    b.setEmployee(emp);
    b.setBookingDate(date);
    b.setNotes(payload.notes());
    b.setRequestedAt(LocalDateTime.now());
    return bookingRepository.save(b);
    }

    // GET ALL
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    // GET BY ID
    public Booking findById(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Prenotazione non trovata"+ id));
    }

    //PUT UPDATE
    public Booking update(UUID id, NewBookingPayload payload) {
        Booking booking = findById(id);
        booking.setNotes(payload.notes());
        return bookingRepository.save(booking);
    }
    // DELETE
    public void delete(UUID id) {
        bookingRepository.deleteById(id);
    }
}
