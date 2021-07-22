package com.example.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    private Calc calc;

    @BeforeEach
    void setUp() {
        calc = new Calc();
    }

    @Test
    public void sumShouldReturnAValidResult() {
        Integer expectedValue = 3 + 5;
        Integer testResult = calc.sum(3, 5);
        assertEquals(expectedValue, testResult, "Sum method does not return a valid result");
    }

    @Test
    public void dividingByZeroShouldThrowAnException() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(3, 0);
        }, "Division method does not throw arithmetic exception.");
    }

    @Test
    public void divisionShouldReturnAValidResult() {
        Double expectedValue = 10.0;
        Double testResult = calc.divide(20, 2);
        assertEquals(expectedValue, testResult, "Division method does not return a valid result");
    }
}