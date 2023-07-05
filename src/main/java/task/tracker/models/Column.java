package task.tracker.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.tracker.enums.ColumnType;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "task_column")
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(0)
    private Integer countOfTasks;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ColumnType columnType;

    @OneToMany(mappedBy = "column")
    private List<Task> tasks;
}
