package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.models.DeadlineDay;
import com.kuhar.tasktracker.models.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import com.kuhar.tasktracker.repositories.DeadlineDayRepository;
import com.kuhar.tasktracker.repositories.TaskRepository;
import com.kuhar.tasktracker.responses.TaskResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final DeadlineDayRepository deadlineDayRepository;

    public TaskResponse create(Task task) throws NotFound {
        var deadline = DeadlineDay.builder()
                .deadlineDate(task.getDeadlineDate())
                .deadlineTime(task.getDeadlineTime())
                .task(task)
                .build();
        taskRepository.save(task);
        deadlineDayRepository.save(deadline);
        return TaskResponse.builder()
                .task(task)
                .deadlineDay(deadline)
                .build();
    }
    public Optional<Task> getById(Integer id){
        return taskRepository.findById(id);
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }

    public Task update(Task task) {
        taskRepository.save(task);
        return task;
    }
}
