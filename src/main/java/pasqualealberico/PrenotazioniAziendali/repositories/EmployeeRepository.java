package pasqualealberico.PrenotazioniAziendali.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pasqualealberico.PrenotazioniAziendali.entities.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByUsername(String username); //TODO <<<<<<<<<<<<<<<<<<<
}
