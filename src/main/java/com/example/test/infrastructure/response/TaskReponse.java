package com.example.test.infrastructure.response;

import com.example.test.domain.TaskStatusEnum;
import com.example.test.domain.models.Customer;
import com.example.test.domain.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskReponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 5994122034100114192L;
    private Integer id;
    private final String description;
    private final String name;
    private final Customer customer;
    private TaskStatusEnum state;
    private final User creationUser;
    private final User modificationUser;
    private final LocalDateTime creationTime;
    private final LocalDateTime modificationTime;
}
