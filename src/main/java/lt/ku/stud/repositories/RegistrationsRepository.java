package lt.ku.stud.repositories;

import lt.ku.stud.entities.Client;
import lt.ku.stud.entities.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationsRepository extends JpaRepository<Registrations, Integer> {
    @Modifying
    @Query("DELETE FROM Workouts w WHERE w.id = :pid")
    void deleteByPid(@Param("pid") Integer theId);
}
