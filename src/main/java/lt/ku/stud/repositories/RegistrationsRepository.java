package lt.ku.stud.repositories;

import lt.ku.stud.entities.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<Registrations, Integer> {
}
