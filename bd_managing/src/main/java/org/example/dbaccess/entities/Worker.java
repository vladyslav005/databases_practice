package org.example.dbaccess.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Worker {
    private String type = "UNDEFINED";
    private int id;
    private String name;
    private LocalDate birthday;
    private String level;
    private int salary;


    public Worker(int id, String name, LocalDate birthday, String level, int salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public Worker(String type, int id, String name, LocalDate birthday, String level, int salary) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }
}
