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
}

