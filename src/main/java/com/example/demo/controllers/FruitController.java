package com.example.demo.controllers;


import com.example.demo.models.Fruit;
import com.example.demo.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class FruitController {

    @Autowired
    private FruitService fruitService;


    @GetMapping("fruits")
    public ArrayList<Fruit> getAllFruits() {
        return fruitService.getFruits();
    }

    @PostMapping("fruits")
    public Fruit storeFruit(@RequestBody String name) {
        return fruitService.addFruit(name);
    }

    @DeleteMapping("fruits/{id}")
    public void deleteFruit(@PathVariable("id") Long id){
        fruitService.deleteFruit(id);
    }

    @PatchMapping("fruits/{id}")
    public ResponseEntity<Object> updateFruit(@PathVariable("id") Long id, @RequestBody String newName) {
        Optional<Fruit> fruit = fruitService.updateFruit(id, newName);
        if(fruit.isPresent()) {
            //send an ok response
//            return ResponseEntity.ok().build();
            return new ResponseEntity<>(fruit.get(), HttpStatus.OK);
        }

        //else return not found
//        return ResponseEntity.notFound().build();
        return new ResponseEntity<>("Fruit not found", HttpStatus.NOT_FOUND);
    }

}
