package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /**
     * Adds two numbers together.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts b from a.
     */
    public double subtract(double a, double b) {
        return a - b;

    }

    /**
     * Multiplies two numbers.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides a by b.
     */
    public double divide(double a, double b) {

         if (b == 0) {
             throw new ArithmeticException("Division by zero is not allowed");
         }
        
         return a / b;
    }

    /**
     * Calculates the power of a number.
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
