package com.kuhar.tasktracker.mappers;

import com.kuhar.tasktracker.models.BaseEntity;

public interface BaseEntityMapper<E extends BaseEntity, DE> {
    DE mapEntityToDto(E e);
}
