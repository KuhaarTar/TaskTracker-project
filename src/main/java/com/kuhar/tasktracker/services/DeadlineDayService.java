package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.models.DeadlineDay;
import com.kuhar.tasktracker.models.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
                        () -> new HttpClientErrorException
                                (HttpStatusCode.valueOf(404))).getTask();
        return Optional.of(task);
    }
}
