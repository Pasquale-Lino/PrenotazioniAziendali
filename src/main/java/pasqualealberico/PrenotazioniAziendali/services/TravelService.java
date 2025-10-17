package pasqualealberico.PrenotazioniAziendali.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pasqualealberico.PrenotazioniAziendali.entities.Travel;
import pasqualealberico.PrenotazioniAziendali.enums.TravelStatus;
import pasqualealberico.PrenotazioniAziendali.exceptions.NotFoundException;
import pasqualealberico.PrenotazioniAziendali.payloads.NewTravelPayload;
import pasqualealberico.PrenotazioniAziendali.repositories.TravelRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TravelService {
    @Autowired private TravelRepository travelRepository;

    // GET ALL - TROVO I VIAGGI
    public Page<Travel> getAll(int page, int size, String sortBy) {
        Pageable p = PageRequest.of(page,size, Sort.by(sortBy));
        return travelRepository.findAll(p);
    }

    // GET BY ID
    public Travel findById(UUID id) {
        return travelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Travel not found with id " + id));
    }

    // POST - CREO VIAGGIO
    public Travel create(NewTravelPayload payload){
        Travel t= new Travel();
        t.setDestination(payload.destination());
        t.setDate(payload.date());
        t.setStatus(TravelStatus.IN_PROGRAM);
        return travelRepository.save(t);
    }

    // UPDATE
    public Travel update(UUID id, NewTravelPayload payload) {
        Travel t = findById(id);
        t.setDestination(payload.destination());
        t.setDate(payload.date());
        //t.setStatus(payload.status());
        return travelRepository.save(t);
    }

    // DELETE
    public void delete(UUID id) {
        travelRepository.deleteById(id);
    }

    // PATCH - CAMBIO STATUS DEL VIAGGIO
    public Travel changeStatus (UUID id, TravelStatus newStatus) {
        Travel t= travelRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        t.setStatus(newStatus);
        return travelRepository.save(t);
    }
}
