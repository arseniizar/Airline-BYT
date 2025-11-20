package com.example.airlinebyt.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Уся ієрархія зберігається в одній таблиці
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING) // Колонка, що визначає тип
@Data // Генерує гетери, сетери, toString() і т.д.
@NoArgsConstructor
public abstract class Person {

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
        return java.time.Period.between(birthDate, LocalDate.now()).getYears();
    }
}
