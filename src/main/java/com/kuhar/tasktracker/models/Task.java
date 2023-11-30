package com.kuhar.tasktracker.models;

import com.kuhar.tasktracker.enums.ColumnType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    private String note;
    @Pattern(regexp = "#.*")
    private String tag;
    private LocalDateTime deadlineDate;
    @Enumerated(EnumType.STRING)
    private ColumnType columnType;
}

