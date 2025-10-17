package pasqualealberico.PrenotazioniAziendali.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pasqualealberico.PrenotazioniAziendali.entities.Employee;
import pasqualealberico.PrenotazioniAziendali.exceptions.NotFoundException;
import pasqualealberico.PrenotazioniAziendali.payloads.NewEmployeePayload;
import pasqualealberico.PrenotazioniAziendali.repositories.EmployeeRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class EmployeeService {
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private Cloudinary imageUploader;

    //GET ALL
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    //GET BY ID
    public Employee findById(UUID id){
        return employeeRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    //POST CREAZIONE DIPENDENTE
    public Employee create(NewEmployeePayload payload){
        Employee emp= new Employee();
        emp.setUsername(payload.username());
        emp.setName(payload.name());
        emp.setCognome(payload.surname());
        emp.setEmail(payload.email());
        //emp.setAvatar(payload.avatar);
        return employeeRepository.save(emp);
    }
   // PUT AGGIORNAMENTO DIPENDENTE
    public Employee update(UUID id, NewEmployeePayload payload) {
        Employee emp = findById(id);
        emp.setUsername(payload.username());
        emp.setName(payload.name());
        emp.setCognome(payload.surname());
        emp.setEmail(payload.email());
        //emp.setAvatar(payload.avatar());
        return employeeRepository.save(emp);
    }

    //  DELETE
    public void delete(UUID id) {
        Employee emp = findById(id);
        employeeRepository.delete(emp);
    }

    //UPLOAD AVATAR
    public Employee uploadAvatar(UUID id, MultipartFile file){
        Employee e= employeeRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        try {
            // Carica l'immagine su Cloudinary
            Map result = imageUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageURL= (String) result.get("url");

            // Aggiorna l'autore con l'URL appena ottenuto
            e.setAvatar(imageURL);

            // Salva e ritorna l'autore aggiornato
            return employeeRepository.save(e);
        } catch (IOException ex) {
            throw new RuntimeException("Errore nel caricamento dell'immagine su Cloudinary",ex);
        }
    }
}
