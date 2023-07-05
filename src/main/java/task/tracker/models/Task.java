package task.tracker.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task.tracker.enums.ColumnType;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Integer id;

    private String note;

    @Pattern(regexp = "#.*")
    private String tag;

    private Date deadlineDate;

    @Enumerated(EnumType.STRING)
    private ColumnType columnType;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private Column column;
}

