package com.example.demo.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    private Boolean inSeason;

    public Fruit() { }

    public Fruit(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getInSeason() { return inSeason; }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsInSeason(Boolean isInSeason) { this.inSeason = isInSeason; }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fruit)) return false;
        Fruit fruit = (Fruit) o;
        return id == fruit.id && name.equals(fruit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
