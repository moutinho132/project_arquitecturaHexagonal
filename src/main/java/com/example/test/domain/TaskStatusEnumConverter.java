package com.example.test.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class TaskStatusEnumConverter implements AttributeConverter<TaskStatusEnum, Integer> {

  @Override
  public Integer convertToDatabaseColumn(final TaskStatusEnum statusEnum) {
    return Objects.nonNull(statusEnum) ? statusEnum.getId() : null;
  }

  @Override
  public TaskStatusEnum convertToEntityAttribute(final Integer statusId) {
    return Objects.nonNull(statusId) ? TaskStatusEnum.getById(statusId) : null;
  }
}
