package com.example.test.application.excepcion;

import java.io.Serial;

public class TaskNotFoundException extends NotFoundException {

  @Serial
  private static final long serialVersionUID = -4859745897388504910L;

  private static final String RESOURCE = "Task";

  public TaskNotFoundException(final Integer id) {
    super(id, RESOURCE);
  }
}
