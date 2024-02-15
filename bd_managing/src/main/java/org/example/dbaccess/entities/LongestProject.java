package org.example.dbaccess.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongestProject {
    private int id;
    private String name;
    private int date_diff;

    public LongestProject(int id, String name, int date_diff) {
        this.id = id;
        this.name = name;
        this.date_diff = date_diff;
    }

    @Override
    public String toString() {
        return "LongestProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_diff=" + date_diff +
                '}';
    }
}
