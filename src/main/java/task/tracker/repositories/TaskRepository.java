package task.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.tracker.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
