package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    /**
     * Adds two numbers together.
     * BUG: This implementation is incorrect!
     */
    public double add(double a, double b) {
        // TODO: Fix this - currently multiplies instead of adding
        return a * b;
    }

    /**
     * Subtracts b from a.
     * BUG: This implementation is incorrect!
     */
    public double subtract(double a, double b) {
        // TODO: Fix this - currently adds instead of subtracting
        return a + b;
    }

    /**
     * Multiplies two numbers.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides a by b.
     * BUG: This implementation doesn't handle division by zero!
     */
    public double divide(double a, double b) {
        // TODO: Fix this - should throw exception or return proper error for division by zero
        return a / b;
    }

    /**
     * Calculates the power of a number.
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
