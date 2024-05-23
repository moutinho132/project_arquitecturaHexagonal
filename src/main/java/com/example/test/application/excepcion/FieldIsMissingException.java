package com.example.test.application.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@AllArgsConstructor
@Getter
public class FieldIsMissingException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -8433363895084938019L;

  private final String name;
  private final String message = "FIELD MANDATORY";
}
