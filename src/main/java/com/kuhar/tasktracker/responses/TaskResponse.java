package com.kuhar.tasktracker.responses;

import com.kuhar.tasktracker.models.DeadlineDay;
import com.kuhar.tasktracker.models.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class TaskResponse {
    private Task task;
    private DeadlineDay deadlineDay;
}
