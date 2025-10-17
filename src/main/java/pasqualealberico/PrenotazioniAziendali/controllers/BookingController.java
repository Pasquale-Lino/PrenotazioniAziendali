package pasqualealberico.PrenotazioniAziendali.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pasqualealberico.PrenotazioniAziendali.entities.Booking;
import pasqualealberico.PrenotazioniAziendali.payloads.NewBookingPayload;
import pasqualealberico.PrenotazioniAziendali.services.BookingService;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable UUID id) {
        return bookingService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@RequestBody @Validated NewBookingPayload payload){
        return bookingService.createBooking(payload);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable UUID id, @RequestBody NewBookingPayload payload) {
        return bookingService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable UUID id) {
        bookingService.delete(id);
    }
}
