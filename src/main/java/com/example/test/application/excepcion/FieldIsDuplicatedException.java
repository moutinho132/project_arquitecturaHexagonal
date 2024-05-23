package com.example.test.application.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@AllArgsConstructor
@Getter
public class FieldIsDuplicatedException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 5235121069078709566L;

  private final String name;
  private final String message = "THIS_FIELD_IS_DUPLICATED";
}
