package pasqualealberico.PrenotazioniAziendali.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewTravelPayload(
        @NotBlank(message = "destination obbligatoria")
        String destination,
        @NotNull(message = "date obbligatoria")
        @FutureOrPresent(message = "la data deve essere odierna o futura")
        LocalDate date
) {}
