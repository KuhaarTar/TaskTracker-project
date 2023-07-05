package task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.tracker.enums.ColumnType;
import task.tracker.models.Column;
import task.tracker.repositories.ColumnRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;

    public Column save(Column column) {
        columnRepository.save(column);
        return column;
    }

    public Optional<Column> findColumnById(Integer id) {
        return columnRepository.findById(id);
    }

    public Optional<List<Column>> findAllColumns() {
        return Optional.of(columnRepository.findAll());
    }

    public void deleteCustomOnly(Column column){
        columnRepository.delete(column);
    }
}
