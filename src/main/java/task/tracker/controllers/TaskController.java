package task.tracker.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.tracker.models.Task;
import task.tracker.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        return ResponseEntity.ok(taskService.create(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id){
        if(taskService.getById(id).isPresent()){
            return ResponseEntity.ok(taskService.getById(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAll());
    }

    @DeleteMapping ResponseEntity<?> delete(Task task){
        taskService.delete(task);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{taskId}/{currentColumnId}")
    public ResponseEntity<Task> moveTask(@PathVariable @NotNull Integer taskId ,
                                         @PathVariable @NotNull Integer currentColumnId){
        return ResponseEntity.ok(taskService.moveTask(taskId,currentColumnId));
    }
}
