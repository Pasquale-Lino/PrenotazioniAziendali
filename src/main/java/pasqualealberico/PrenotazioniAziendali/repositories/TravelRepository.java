package pasqualealberico.PrenotazioniAziendali.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pasqualealberico.PrenotazioniAziendali.entities.Travel;

import java.util.UUID;

public interface TravelRepository extends JpaRepository<Travel, UUID> {

}

