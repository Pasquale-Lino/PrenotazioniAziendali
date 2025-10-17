package pasqualealberico.PrenotazioniAziendali.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEmployeePayload(
        @NotBlank(message = "username obbligatoria")
        String username,
        @NotBlank(message = "nome obbligatoria")
        String name,
        @NotBlank(message = "cognome obbligatoria")
        String cognome,
        @Email @NotBlank
        String email
) {}
