package com.kuhar.tasktracker.repositories;

import com.kuhar.tasktracker.models.enums.ColumnType;
import com.kuhar.tasktracker.models.Task;
import com.kuhar.tasktracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);

    List<Task> findAllByColumnTypeAndDeadlineDateAfter(ColumnType type, LocalDateTime time);
}
