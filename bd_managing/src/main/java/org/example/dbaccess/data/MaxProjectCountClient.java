package org.example.dbaccess.data;


import lombok.Data;

@Data
public class MaxProjectCountClient {
    private int id;
    private String name;
    private int projectCount;

    public MaxProjectCountClient(int id, String name, int projectCount) {
        this.id = id;
        this.name = name;
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        return "MaxProjectCountClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectCount=" + projectCount +
                '}';
    }
}