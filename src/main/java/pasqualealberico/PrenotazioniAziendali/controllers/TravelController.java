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
@RequestMapping("/travels")
public class TravelController {
    @Autowired TravelService travelService;

    @GetMapping public Page<Travel> getAll(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "date")String sortBy){
        return travelService.getAll(page,size,sortBy);
    }

    @GetMapping("/{id}")
    public Travel getTravelById(@PathVariable UUID id) {
        return travelService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Travel create(@RequestBody @Validated NewTravelPayload payload){
        return travelService.create(payload);
    }

    @PutMapping("/{id}")
    public Travel updateTravel(@PathVariable UUID id, @RequestBody NewTravelPayload payload) {
        return travelService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    public void deleteTravel(@PathVariable UUID id) {
        travelService.delete(id);
    }

    @PostMapping("/{id}/status")
    public Travel updateStatus(@PathVariable UUID id, @RequestParam TravelStatus newStatus){
        return travelService.changeStatus(id,newStatus);
    }


}
