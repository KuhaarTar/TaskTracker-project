package task.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.tracker.models.DeadlineDay;

import java.util.Date;

@Repository
public interface DeadlineDayRepository extends JpaRepository<DeadlineDay,Integer> {

    DeadlineDay findByDeadlineDate(Date date);
}
