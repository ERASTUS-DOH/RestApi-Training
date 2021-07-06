package com.example.demo.controllers;


import com.example.demo.models.Fruit;
import com.example.demo.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class FruitController {

    @Autowired
    private FruitService fruitService;

//    public FruitController(FruitService fruitService){
//        this.fruitService = fruitService;
//    }


    @GetMapping("fruits")
    public ArrayList<Fruit> getAllFruits() {
        return fruitService.getFruits();
    }

    @PostMapping("fruits")
    public Fruit storeFruit(@RequestBody String name) {
        return fruitService.addFruit(name);
    }

    @DeleteMapping("fruits/{id}")
    public void deleteFruit(@PathVariable("id") int id){
        fruitService.deleteFruit(id);
    }

    @PatchMapping("fruits/{id}")
    public Fruit updateFruit(@PathVariable("id") int id, @RequestBody String newName) {
        return fruitService.updateFruit(id, newName);
    }

}
