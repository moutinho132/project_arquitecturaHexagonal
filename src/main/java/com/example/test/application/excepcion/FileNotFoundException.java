package com.example.test.application.excepcion;

import java.io.Serial;

public class FileNotFoundException extends NotFoundException {

  @Serial
  private static final long serialVersionUID = 5535180973179563612L;

  private static final String RESOURCE = "file";

  public FileNotFoundException(final Integer id) {
    super(id, RESOURCE);
  }
}
