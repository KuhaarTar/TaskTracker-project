package com.kuhar.tasktracker.models;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.kuhar.tasktracker.enums.ColumnType;

import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalDate deadlineDate;
    private LocalTime deadline;

    @Enumerated(EnumType.STRING)
    private ColumnType columnType;
}

