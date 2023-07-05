package task.tracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.tracker.models.DeadlineDay;
import task.tracker.services.DeadlineDayService;

import java.util.List;

@RestController
@RequestMapping("/deadlinedays")
@RequiredArgsConstructor
public class DeadlineDayController {

    private final DeadlineDayService deadlineDayService;

    @GetMapping
    public ResponseEntity<List<DeadlineDay>> getAll() {
        if (deadlineDayService.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deadlineDayService.getAll());
    }
}
