package com.example.demo.controllers;


import com.example.demo.models.Fruit;
import com.example.demo.repository.FruitRepository;
import com.example.demo.services.FruitService;
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
    private FruitService fruitService;


    @GetMapping("fruits")
    public ResponseEntity<List<Fruit>> getFruits(){
        List<Fruit> fruits = fruitService.getAllFruits();
        return new ResponseEntity<>(fruits, HttpStatus.OK);
    }

    @GetMapping("fruitByName")
    public ResponseEntity<List<Fruit>> getFruitByName(@RequestParam(required = false) String name) {
        try {
            if (name == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            } else {
                List<Fruit> fruitList = fruitService.getFruitByName(name);
                return new ResponseEntity<>(fruitList, HttpStatus.OK);
            }
        }
        // Exceptions are unexpected runtime situations.
        // eg. FileNotFound, IOException (can have multiple caught, also nested)
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("fruits/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable("id") long id) {
       return fruitService.getFruitById(id).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
               .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("fruits")
    public ResponseEntity<Fruit> storeFruit(@RequestBody Fruit fruit) {
      return fruitService.storeFruit(fruit);
    }

    @DeleteMapping("fruits/{id}")
    public ResponseEntity<String> deleteFruit(@PathVariable("id") Long id) {
        return fruitService.deleteFruitById(id);
    }

    @DeleteMapping("fruits")
    public ResponseEntity<String> deleteAllFruits() {
       return fruitService.deleteFruitAll();
    }

    @PatchMapping("fruits/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable("id") Long id, @RequestBody Fruit newFruit) {
        return fruitService.updateFruit(id, newFruit);
    }

    @GetMapping("fruits/inSeason")
    public ResponseEntity<List<Fruit>> findByInSeason() {
        return fruitService.findByInSeason();
    }

}
