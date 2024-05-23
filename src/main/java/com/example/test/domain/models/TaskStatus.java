package com.example.test.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TaskStatus {

  private final Integer id;
  private final String name;
}
