package com.kuhar.tasktracker.mappers;

import com.kuhar.tasktracker.annotations.Mapper;
import com.kuhar.tasktracker.dto.TaskDto;
import com.kuhar.tasktracker.models.Task;

@Mapper
public class TaskDtoMapper implements BaseEntityMapper<Task, TaskDto> {
    @Override
    public TaskDto mapEntityToDto(Task task) {
        return TaskDto.builder()
                .tag(task.getTag())
                .columnType(task.getColumnType())
                .deadlineDate(task.getDeadlineDate())
                .note(task.getNote())
                .userId(task.getUser().getId())
                .build();
    }
}
