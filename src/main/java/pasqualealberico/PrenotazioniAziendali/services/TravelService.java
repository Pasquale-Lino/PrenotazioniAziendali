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

import java.util.UUID;

@Service
public class TravelService {
    @Autowired private TravelRepository travelRepository;

    public Travel create(NewTravelPayload payload){
        Travel t= new Travel();
        t.setDestination(payload.destination());
        t.setDate(payload.date());
        t.setStatus(TravelStatus.IN_PROGRAM);

        return travelRepository.save(t);
    }

    public Travel changeStatus (UUID id, TravelStatus newStatus) {
        Travel t= travelRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        t.setStatus(newStatus);
        return travelRepository.save(t);
    }
    public Page<Travel> findAll(int page, int size, String sortBy) {
        Pageable p = PageRequest.of(page,size, Sort.by(sortBy));
        return travelRepository.findAll(p);
    }
}
