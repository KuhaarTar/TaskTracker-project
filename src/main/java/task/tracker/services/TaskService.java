package task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import task.tracker.models.Task;
import task.tracker.repositories.ColumnRepository;
import task.tracker.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final ColumnRepository columnRepository;

    public Task create(Task task) throws NotFound {
        var column = columnRepository
                .findByColumnType(task.getColumnType());
        column.getTasks().add(task);
        columnRepository.save(column);
        taskRepository.save(task);
        return task;
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

    public Task moveTask(Integer taskId ,
                         Integer currentColumnId ) {
        var task = taskRepository.findById(taskId);
        var currentColumn = columnRepository.findById(currentColumnId);
        if (task.isEmpty() || currentColumn.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
        var destinationColumn = columnRepository.findByColumnType(task.get().getColumnType());
        currentColumn.get().getTasks().remove(task.get());
        destinationColumn.getTasks().add(task.get());
        columnRepository.save(currentColumn.get());
        columnRepository.save(destinationColumn);
        return task.get();
    }
}
