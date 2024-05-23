package com.example.test.domain.entities;

import com.example.test.domain.TaskStatusEnum;
import com.example.test.domain.TaskStatusEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "t_task")
public class TaskEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5560271806265311713L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "status")
    @Convert(converter = TaskStatusEnumConverter.class)
    private TaskStatusEnum state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creation_user_id")
    private UserEntity creationUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modification_user_id")
    private UserEntity modificationUser;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "modification_time")
    private LocalDateTime modificationTime;
}
