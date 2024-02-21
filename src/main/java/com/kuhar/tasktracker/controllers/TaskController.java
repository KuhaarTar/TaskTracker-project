package com.kuhar.tasktracker.controllers;

import com.kuhar.tasktracker.models.dto.TaskDto;
import com.kuhar.tasktracker.models.enums.ColumnType;
import com.kuhar.tasktracker.models.Task;
import com.kuhar.tasktracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeColumnType(@RequestParam ColumnType type,
                                              @PathVariable Long id) {
        return ResponseEntity.ok(taskService.changeColumnType(type, id));
    }

    @PatchMapping
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto updatedTask) {
        return ResponseEntity.ok(taskService.update(updatedTask));
    }
}
