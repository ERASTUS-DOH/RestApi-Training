package com.example.demo.controllers;


import com.example.demo.models.Fruit;
import com.example.demo.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FruitController {

    @Autowired
    private FruitRepository fruitRepository;

    @GetMapping("fruits")
    public ResponseEntity<List<Fruit>> getFruits(@RequestParam(required = false) String name) {
        try {
            ArrayList<Fruit> fruits = new ArrayList<>();
            if (name == null) {
                fruitRepository.findAll().forEach(fruits::add);
            } else {
                fruitRepository.findByName(name).forEach(fruits::add);
            }
            if (fruits.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(fruits, HttpStatus.OK);
        }
        // Exceptions are unexpected runtime situations.
        // eg. FileNotFound, IOException (can have multiple caught, also nested)
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("fruits/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable("id") long id) {
        Optional<Fruit> fruit = fruitRepository.findById(id);

        if (fruit.isPresent()) {
            return new ResponseEntity<>(fruit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("fruits")
    public ResponseEntity<Fruit> storeFruit(@RequestBody Fruit fruit) {
        /* Look into potentially throwing your own exceptions!
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User already exist with email");
        }*/
        try {
            Fruit _fruit = fruitRepository.save(new Fruit(fruit.getName()));
            return new ResponseEntity<>(_fruit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("fruits/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable("id") Long id) {
        Optional<Fruit> fruit = fruitRepository.findById(id);

        if (fruit.isPresent()) {
            fruitRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No such id", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("fruits")
    public ResponseEntity<String> deleteAllFruits(@PathVariable("id") Long id) {
        fruitRepository.deleteAll();
        return new ResponseEntity<>("All fruits deleted", HttpStatus.OK);
    }

    @PatchMapping("fruits/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable("id") Long id, @RequestBody Fruit newFruit) {
        // Option 1. presence check - update if found, else return not found response
        Optional<Fruit> fruit = fruitRepository.findById(id);

        // this requires all data to be entered - you can do more granular checks
        if (fruit.isPresent()){
            fruit.get().setName(newFruit.getName());
            fruit.get().setIsInSeason(newFruit.getInSeason());
            return new ResponseEntity<>(fruitRepository.save(fruit.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Option 2. using map, create if not found
    }

    @GetMapping("fruits/inSeason")
    public ResponseEntity<List<Fruit>> findByInSeason() {
        ArrayList<Fruit> fruitsInSeason = new ArrayList<>();
        return new ResponseEntity<>(fruitRepository.findByInSeason(true), HttpStatus.OK);
    }

}
