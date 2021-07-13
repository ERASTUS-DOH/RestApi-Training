package com.example.demo;

import com.example.demo.controllers.FruitController;
import com.example.demo.repository.FruitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FruitController.class)
// for testing the controller layer - you mock other dependencies
public class FruitControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper; // to write strings

    @MockBean
    FruitRepository fruitRepository;

    @Test
    public void getAllFuitsTest() throws Exception {

    }
}
