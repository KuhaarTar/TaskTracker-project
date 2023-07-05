package task.tracker.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.tracker.enums.ColumnType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "deadlineDay_id")
    private DeadlineDay deadlineDay;

    private String note;

    @Pattern(regexp = "#.*")
    private String tag;

    private ColumnType columnType;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private Column column;
}
