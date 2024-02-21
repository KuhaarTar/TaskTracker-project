package com.kuhar.tasktracker.services.scheduling;

import com.kuhar.tasktracker.models.enums.ColumnType;
import com.kuhar.tasktracker.models.Task;
import com.kuhar.tasktracker.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskSchedulingService implements TaskScheduling {
    private static final String EVERY_DAY = "0 0 0 * * *";
    private final TaskRepository taskRepository;

    @Override
    @Scheduled(cron = EVERY_DAY)
    public void deleteConfirmedTasksAfterEveryDay() {
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        List<Task> tasksToDelete = taskRepository.
                findAllByColumnTypeAndDeadlineDateAfter(ColumnType.DONE, time);
        taskRepository.deleteAll(tasksToDelete);
    }
}
