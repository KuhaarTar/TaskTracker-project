package task.tracker.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.tracker.enums.ColumnType;
import task.tracker.models.Column;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Integer> {

    Column findByColumnType(@NotNull ColumnType columnType);
}
