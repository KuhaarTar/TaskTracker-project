package task.tracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.tracker.enums.ColumnType;
import task.tracker.models.Column;
import task.tracker.services.ColumnService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/columns")
@RequiredArgsConstructor
public class ColumnController {

    private final List<Column> defaultColumns = new ArrayList<>();

    private final ColumnService columnService;

    @PostMapping
    public ResponseEntity<Column> saveCustom(@RequestBody Column column) {
        if (column.getColumnType() != ColumnType.CUSTOM) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(columnService.save(column));
    }

    @GetMapping
    private ResponseEntity<List<Column>> getAll() {
        if (columnService.findAllColumns().isPresent()) {
            return ResponseEntity.ok(columnService.findAllColumns().get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/default")
    public ResponseEntity<List<Column>> getDefaultColumns() {
        var todo = Column.builder()
                .countOfTasks(0)
                .columnType(ColumnType.TODO)
                .build();

        var inProgress = Column.builder()
                .countOfTasks(0)
                .columnType(ColumnType.IN_PROGRESS)
                .build();

        var done = Column.builder()
                .countOfTasks(0)
                .columnType(ColumnType.DONE)
                .build();
        Collections.addAll(defaultColumns, todo, inProgress, done);
        columnService.saveAll(defaultColumns);
        return ResponseEntity.ok(defaultColumns);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCustom(Column column) {
        if (column.getColumnType() != ColumnType.CUSTOM) {
            return ResponseEntity.badRequest().build();
        }
        columnService.deleteCustomOnly(column);
        return ResponseEntity.ok().build();
    }
}
