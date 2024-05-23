package com.example.test.infrastructure.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 614015548881635883L;


  @Valid
  @NotNull(message = "THIS_FIELD_IS_MANDATORY_MESSAGE")
  private PersonRequest person;

  private String reference;

}
