package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.dto.TaskDto;
import com.kuhar.tasktracker.enums.ColumnType;
import com.kuhar.tasktracker.exceptions.PermissionDeniedException;
import com.kuhar.tasktracker.mappers.TaskDtoMapper;
import com.kuhar.tasktracker.models.Task;
import com.kuhar.tasktracker.models.User;
import com.kuhar.tasktracker.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskDtoMapper taskDtoMapper;
    private final ApplicationAudit audit;

    public TaskDto create(Task task) {
        User principal = audit.returnCurrentUser();
        task.setUser(principal);
        taskRepository.save(task);
        return taskDtoMapper.mapEntityToDto(task);
    }

    public TaskDto getById(Long id) {
        User principal = audit.returnCurrentUser();
        Task task = getTaskByUserAndId(principal, id);
        return taskDtoMapper.mapEntityToDto(task);
    }

    public List<TaskDto> getAll() {
        User principal = audit.returnCurrentUser();
        List<Task> tasks = taskRepository.findAllByUser(principal);
        return tasks.stream().map(taskDtoMapper::mapEntityToDto).toList();
    }

    public void delete(Long taskId) {
        Task task = getTaskByUserAndId(audit.returnCurrentUser(), taskId);
        taskRepository.delete(task);
    }

    public TaskDto update(String tag, String note, Long taskId) {
        Task task = getTaskByUserAndId(audit.returnCurrentUser(), taskId);
        task.setTag(tag);
        task.setNote(note);
        return taskDtoMapper.mapEntityToDto(task);
    }

    public void changeColumnType(ColumnType columnType, Long taskId) {
        Task task = getTaskByUserAndId(audit.returnCurrentUser(), taskId);
        task.setColumnType(columnType);
    }

    private Task getTaskByUserAndId(User principal, Long taskId) {
        Task usersTask = taskRepository.getReferenceById(taskId);
        if (!usersTask.getUser().equals(principal)) {
            throw new PermissionDeniedException("You do not have permission to tas by id: " + taskId);
        }
        return usersTask;
    }

}
