package com.example.test.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "t_people")
public class PersonEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4065082902092960044L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Formula("concat_ws(' ',name,surname)")
    private String fullName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
