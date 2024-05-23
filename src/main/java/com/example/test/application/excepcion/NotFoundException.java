package com.example.test.application.excepcion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 4620034341502172002L;

  private Integer id;
  private String resource;
}
