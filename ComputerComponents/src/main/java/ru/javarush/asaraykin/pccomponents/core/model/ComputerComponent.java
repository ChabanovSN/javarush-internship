package ru.javarush.asaraykin.pccomponents.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "components", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "name"})
})
public class ComputerComponent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer isNecessary;

    private Integer amount;

    public ComputerComponent() {
    }

    public ComputerComponent(String name, Integer isNecessary, Integer amount) {
        this.name = name;
        this.isNecessary = isNecessary;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ComputerComponent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isNecessary=" + isNecessary +
                ", amount=" + amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(Integer isNecessary) {
        this.isNecessary = isNecessary;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
