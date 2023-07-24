package com.kuhar.tasktracker.controllers;

import com.kuhar.tasktracker.models.DeadlineDay;
import com.kuhar.tasktracker.models.Task;
import com.kuhar.tasktracker.services.DeadlineDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deadlines")
@RequiredArgsConstructor
public class DeadlineDayController {

    private final DeadlineDayService deadlineDayService;

    @GetMapping
    public ResponseEntity<List<DeadlineDay>> getAll() {
        return ResponseEntity.ok(deadlineDayService.getAll());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskByDeadlineDayId(@PathVariable Integer id) {
        if (deadlineDayService.getTaskByDeadlineDayId(id).isPresent()) {
            return ResponseEntity.ok(deadlineDayService.getTaskByDeadlineDayId(id).get());
        }
        return ResponseEntity.notFound().build();
    }
}
