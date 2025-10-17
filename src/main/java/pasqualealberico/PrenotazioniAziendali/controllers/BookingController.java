package pasqualealberico.PrenotazioniAziendali.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pasqualealberico.PrenotazioniAziendali.entities.Booking;
import pasqualealberico.PrenotazioniAziendali.payloads.NewBookingPayload;
import pasqualealberico.PrenotazioniAziendali.services.BookingService;

import java.awt.print.Book;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired BookingService bookingService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@RequestBody @Validated NewBookingPayload payload){
        return bookingService.createBooking(payload);
    }
}
