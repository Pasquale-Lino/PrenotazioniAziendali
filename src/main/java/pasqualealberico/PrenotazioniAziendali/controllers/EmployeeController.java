package pasqualealberico.PrenotazioniAziendali.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pasqualealberico.PrenotazioniAziendali.entities.Employee;
import pasqualealberico.PrenotazioniAziendali.payloads.NewEmployeePayload;
import pasqualealberico.PrenotazioniAziendali.services.EmployeeService;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired EmployeeService employeeService;

    @PatchMapping("/{id}/avatar")
    public Employee uploadAvatar(@PathVariable UUID id, @RequestParam("avatar")MultipartFile file){
        return employeeService.uploadAvatar(id,file);
    }

    //@PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    //public Employee create(@RequestBody @Validated NewEmployeePayload payload){
    //      Crea employee mapping payload -> entity
    //     return employeeService.createEmployee(payload);

    // }

}
