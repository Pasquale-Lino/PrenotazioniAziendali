package pasqualealberico.PrenotazioniAziendali.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NewBookingPayload(
        @NotNull(message = "id del viaggio obbligatoria")
        UUID TravelId,
        @NotNull(message = "id del dipendente obbligatoria")
        UUID employeeId,
        @NotNull(message = "data prenotazione obbligatoria")
        LocalDate bookingDate,
        @Size(max=500)
        String notes
) {}
