package com.example.demo.services;

public class Calc {
    public Integer sum(Integer a, Integer b) {
        return a + b;
    }

    public Double divide(Integer num, Integer den) {
       if(den == 0) throw new ArithmeticException("Denominator cannot be 0");
        return num.doubleValue() / den.doubleValue();
    }

}
