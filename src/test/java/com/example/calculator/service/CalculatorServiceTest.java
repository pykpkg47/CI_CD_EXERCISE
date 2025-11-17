package com.example.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Service Unit Tests")
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    @DisplayName("Addition should return correct sum")
    void testAddition() {
        // This test will FAIL because add() is incorrectly implemented
        double result = calculatorService.add(5.0, 3.0);
        assertEquals(8.0, result, 0.001,
            "5 + 3 should equal 8, but the implementation is wrong!");
    }

    @Test
    @DisplayName("Addition with negative numbers")
    void testAdditionWithNegatives() {
        // This test will FAIL
        double result = calculatorService.add(-5.0, 3.0);
        assertEquals(-2.0, result, 0.001,
            "-5 + 3 should equal -2");
    }

    @Test
    @DisplayName("Subtraction should return correct difference")
    void testSubtraction() {
        // This test will FAIL because subtract() is incorrectly implemented
        double result = calculatorService.subtract(10.0, 4.0);
        assertEquals(6.0, result, 0.001,
            "10 - 4 should equal 6, but the implementation is wrong!");
    }

    @Test
    @DisplayName("Subtraction with negative result")
    void testSubtractionNegativeResult() {
        // This test will FAIL
        double result = calculatorService.subtract(3.0, 8.0);
        assertEquals(-5.0, result, 0.001,
            "3 - 8 should equal -5");
    }

    @Test
    @DisplayName("Multiplication should work correctly")
    void testMultiplication() {
        // This test should PASS - multiplication is correctly implemented
        double result = calculatorService.multiply(4.0, 5.0);
        assertEquals(20.0, result, 0.001);
    }

    @Test
    @DisplayName("Division should return correct quotient")
    void testDivision() {
        // This test should PASS for normal division
        double result = calculatorService.divide(10.0, 2.0);
        assertEquals(5.0, result, 0.001);
    }

    @Test
    @DisplayName("Division by zero should throw exception")
    void testDivisionByZero() {
        // This test will FAIL because divide() doesn't handle division by zero
        assertThrows(ArithmeticException.class, () -> {
            calculatorService.divide(10.0, 0.0);
        }, "Division by zero should throw ArithmeticException!");
    }

    @Test
    @DisplayName("Power calculation should work correctly")
    void testPower() {
        // This test should PASS
        double result = calculatorService.power(2.0, 3.0);
        assertEquals(8.0, result, 0.001);
    }
}
