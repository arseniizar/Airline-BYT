package com.example.airlinebyt.models.person;

import com.example.airlinebyt.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Уся ієрархія зберігається в одній таблиці
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING) // Колонка, що визначає тип
@Data // Генерує гетери, сетери, toString() і т.д.
@NoArgsConstructor
public abstract class Person implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    // Похідний атрибут /age реалізується методом, а не полем в БД
    @Transient // Не зберігати це поле в базі даних
    public Integer getAge() {
        if (birthDate == null) {
            return null;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
