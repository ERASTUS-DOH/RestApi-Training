package com.example.demo.services;

import com.example.demo.models.Fruit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FruitService {
    private ArrayList<Fruit> fruits = new ArrayList<>();

    private int generateId() {
     return fruits.size() + 1;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public Fruit addFruit(String name) {
        Fruit newFruit = new Fruit(generateId(), name);
        fruits.add(newFruit);
        return newFruit;
    }

    public void deleteFruit(int id) {
       Optional<Fruit> fruit = fruits.stream().filter(f -> f.getId() == id).findFirst();
        fruit.ifPresent(value -> fruits.remove(value));
    }

    public Fruit updateFruit(int id, String newName) {
        Optional<Fruit> fruit = fruits.stream().filter(f -> f.getId() == id).findFirst();
        fruit.ifPresent(value -> value.setName(newName));
        return fruit.get();
    }
}
