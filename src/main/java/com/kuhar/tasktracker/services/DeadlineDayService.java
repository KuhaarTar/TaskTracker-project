package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.models.DeadlineDay;
import com.kuhar.tasktracker.models.Task;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kuhar.tasktracker.repositories.DeadlineDayRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeadlineDayService {

    private final DeadlineDayRepository deadlineDayRepository;

    public List<DeadlineDay> getAll() {
        return deadlineDayRepository.findAll();
    }

    public Optional<Task> getTaskByDeadlineDayId(Integer id) {
        Task task = deadlineDayRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Not found such task")).getTask();
        return Optional.of(task);
    }
}
