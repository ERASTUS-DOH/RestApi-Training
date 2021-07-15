package com.example.demo.services;

import com.example.demo.models.Fruit;
import com.example.demo.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FruitService {
//    private ArrayList<Fruit> fruits = new ArrayList<>();
//
//    public ArrayList<Fruit> getFruits() {
//        return fruits;
//    }
//
//    public Fruit addFruit(String name) {
//        Fruit newFruit = new Fruit(name);
//        fruits.add(newFruit);
//        return newFruit;
//    }
//
//    public void deleteFruit(Long id) {
//       Optional<Fruit> fruit = fruits.stream().filter(f -> f.getId() == id).findFirst();
//       fruit.ifPresent(value -> fruits.remove(value));
//    }
//
//    public Optional<Fruit> updateFruit(Long id, String newName) {
//        Optional<Fruit> fruit = fruits.stream().filter(f -> f.getId() == id).findFirst();
//        fruit.ifPresent(value -> value.setName(newName));
//        return fruit;
//    }
    @Autowired
    private FruitRepository fruitRepository;

    public List<Fruit> getAllFruits(){
      return fruitRepository.findAll();
    }

    public List<Fruit> getFruitByName(String name){
        return fruitRepository.findByName(name);
    }

    public Optional<Fruit>getFruitById(Long id){
        return fruitRepository.findById(id);
    }

    public ResponseEntity<Fruit> storeFruit(Fruit fruit){
         /* Look into potentially throwing your own exceptions!
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User already exist with email");
        }*/
        try {
            Fruit _fruit = fruitRepository.save(fruit);
            return new ResponseEntity<>(_fruit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteFruitById(Long id){
        Optional<Fruit> fruit = fruitRepository.findById(id);
        if (fruit.isPresent()) {
            fruitRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No such id", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteFruitAll(){
        fruitRepository.deleteAll();
        return new ResponseEntity<>("All fruits deleted", HttpStatus.OK);
    }

    public ResponseEntity<Fruit> updateFruit(Long id,Fruit newFruit){
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

    public ResponseEntity<List<Fruit>> findByInSeason() {
        return new ResponseEntity<>(fruitRepository.findByInSeason(true), HttpStatus.OK);
    }
}
