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
    // implicit dependency injection
    private FruitRepository fruitRepository;

    /* private final FruitRepository fruitRepository;
     FruitController(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    */

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
        // finally - clean up (eg. closing resources)
    }

    @GetMapping("fruits/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable("id") long id){
        Optional<Fruit> fruit = fruitRepository.findById(id);

        if (fruit.isPresent()){
            return new ResponseEntity<>(fruit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("fruits")
    public ResponseEntity<Fruit> storeFruit(@RequestBody Fruit fruit) {
        try {
            Fruit _fruit = fruitRepository.save(new Fruit(fruit.getName()));
            return new ResponseEntity<>(_fruit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("fruits/{id}")
    public void deleteFruit(@PathVariable("id") Long id){
        try {

        } catch (Exception e) {

        }
    }

    @DeleteMapping("fruits")
    public void deleteAllFruits(@PathVariable("id") Long id){
        try {

        } catch (Exception e) {

        }
    }

    @PatchMapping("fruits/{id}")
    public void updateFruit(@PathVariable("id") Long id, @RequestBody Fruit newFruit) {
        Optional<Fruit> fruit = fruitRepository.findById(id);

        // Two ways
        // 1. presence check - update if found, else return not found response
        // 2. map through to update, create if not found
    }

    @GetMapping("fruits/inSeason")
    public void findByInSeason() {
        try {

        } catch (Exception e) {

        }
    }

}
