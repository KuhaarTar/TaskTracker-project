package com.kuhar.tasktracker.models.dto;

import com.kuhar.tasktracker.models.enums.ColumnType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDto(Long taskId,
                      Long userId,
                      String note,
                      String tag,
                      LocalDateTime deadlineDate,
                      ColumnType columnType) {
}
