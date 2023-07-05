package task.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.tracker.models.DeadlineDay;

@Repository
public interface DeadlineDayRepository extends JpaRepository<DeadlineDay,Integer> {
}
