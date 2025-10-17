package pasqualealberico.PrenotazioniAziendali.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Record non trovato: "+ id);
    }
}
