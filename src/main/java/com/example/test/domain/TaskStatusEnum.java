package com.example.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

  OPEN(1),
  COMPLETED(2),
  ACCEPTED(3),
  CLOSED(4);

  private final Integer id;

  public static TaskStatusEnum getById(final Integer statusId) {
    return Arrays.stream(TaskStatusEnum.values())
        .filter(e -> e.getId().intValue() == statusId)
        .findAny()
        .orElse(null);
  }
}
