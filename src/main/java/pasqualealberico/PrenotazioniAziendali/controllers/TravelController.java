package pasqualealberico.PrenotazioniAziendali.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pasqualealberico.PrenotazioniAziendali.entities.Travel;
import pasqualealberico.PrenotazioniAziendali.enums.TravelStatus;
import pasqualealberico.PrenotazioniAziendali.payloads.NewTravelPayload;
import pasqualealberico.PrenotazioniAziendali.services.TravelService;

import java.util.UUID;

@RestController
@RequestMapping
public class TravelController {
    @Autowired TravelService travelService;

    @GetMapping public Page<Travel> getAll(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "date")String sortBy){
        return travelService.findAll(page,size,sortBy);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Travel create(@RequestParam@Validated NewTravelPayload payload){
        return travelService.create(payload);
    }

    @PostMapping("/{id}/status")
    public Travel updateStatus(@PathVariable UUID id, @RequestParam TravelStatus newStatus){
        return travelService.changeStatus(id,newStatus);
    }


}
