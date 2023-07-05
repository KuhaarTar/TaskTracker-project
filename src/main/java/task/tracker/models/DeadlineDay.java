package task.tracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeadlineDay {
    @Id
    @GeneratedValue
    private Integer id;

    private Date deadlineDate;

    @OneToOne(mappedBy = "deadlineDay")
    private Task task;
}
