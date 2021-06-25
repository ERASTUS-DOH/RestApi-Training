package com.example.demo.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LibraryController {
    //["Orange", "Pear"]
    ArrayList<String> fruitLists = new ArrayList<>();

    @GetMapping("fruits")
    public ArrayList<String> getAllFruits() {
        return fruitLists;
    }

    @PostMapping("fruits")
    public ArrayList<String> storeFruit(@RequestParam("fruit") String fruit) {
        fruitLists.add(fruit);
        return fruitLists;
    }



    @RequestMapping(value="/testEndpoint", method= RequestMethod.GET)
    public String test(){
        return "Hello World";
    }

    @RequestMapping(value="/testEndpoint/{id}", method = RequestMethod.POST)
    public Integer test2(@PathVariable("id") Integer id){
        return id;
    }
}
