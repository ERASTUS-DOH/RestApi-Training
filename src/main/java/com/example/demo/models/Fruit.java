package com.example.demo.models;

import java.util.Objects;

public class Fruit {
    private int id;
    private String name;

    public Fruit() { }

    public Fruit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
