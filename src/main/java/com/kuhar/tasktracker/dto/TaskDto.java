package com.kuhar.tasktracker.dto;

import com.kuhar.tasktracker.enums.ColumnType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDto(Long userId,
                      String note,
                      String tag,
                      LocalDateTime deadlineDate,
                      ColumnType columnType) {
}
