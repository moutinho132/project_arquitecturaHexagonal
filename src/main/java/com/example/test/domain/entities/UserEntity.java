package com.example.test.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "t_user")
public class UserEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -7853544041236457000L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @Column(name = "surname", nullable = false, length = 128)
  private String surname;

  @Column(name = "email", nullable = false, length = 128, unique = true)
  private String email;

  @Formula("concat_ws(' ',name,surname,email)")
  private String fullName;

  @Column(name = "paasword", nullable = false, length = 128, unique = true)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
  @JoinTable(
          name = "t_task_user",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "task_id"))
  @With
  private List<TaskEntity> tasks;


  /**/
}
