package lt.ku.stud.repositories;

import lt.ku.stud.entities.Workouts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutsRepository extends JpaRepository<Workouts, Integer> {
}
