package pasqualealberico.PrenotazioniAziendali.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pasqualealberico.PrenotazioniAziendali.entities.Employee;
import pasqualealberico.PrenotazioniAziendali.exceptions.BadRequestException;
import pasqualealberico.PrenotazioniAziendali.payloads.NewEmployeePayload;
import pasqualealberico.PrenotazioniAziendali.services.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable UUID id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create (@RequestBody @Validated NewEmployeePayload payload, BindingResult validation){
        if (validation.hasErrors()){

            // Converti la lista di errori in una singola stringa leggibile
            String errorMessage = validation.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                    .orElse("Richiesta non valida");
            throw new BadRequestException(errorMessage);
        }
        return employeeService.create(payload);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable UUID id, @RequestBody NewEmployeePayload payload) {
        return employeeService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        employeeService.delete(id);
    }

    @PatchMapping("/{id}/avatar")
    public Employee uploadAvatar(@PathVariable UUID id, @RequestParam("avatar")MultipartFile file){
        return employeeService.uploadAvatar(id,file);
    }

}
