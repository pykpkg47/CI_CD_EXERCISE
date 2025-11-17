package com.example.calculator.controller;

import com.example.calculator.dto.CalculationRequest;
import com.example.calculator.dto.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
@Tag(name = "Calculator", description = "Simple calculator API for CI/CD demonstration")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/add")
    @Operation(summary = "Add two numbers", description = "Returns the sum of two numbers")
    public ResponseEntity<CalculationResponse> add(@RequestBody CalculationRequest request) {
        double result = calculatorService.add(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "addition"));
    }

    @PostMapping("/subtract")
    @Operation(summary = "Subtract two numbers", description = "Returns the difference of two numbers (a - b)")
    public ResponseEntity<CalculationResponse> subtract(@RequestBody CalculationRequest request) {
        double result = calculatorService.subtract(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "subtraction"));
    }

    @PostMapping("/multiply")
    @Operation(summary = "Multiply two numbers", description = "Returns the product of two numbers")
    public ResponseEntity<CalculationResponse> multiply(@RequestBody CalculationRequest request) {
        double result = calculatorService.multiply(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "multiplication"));
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide two numbers", description = "Returns the quotient of two numbers (a / b)")
    public ResponseEntity<CalculationResponse> divide(@RequestBody CalculationRequest request) {
        double result = calculatorService.divide(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "division"));
    }

    @PostMapping("/power")
    @Operation(summary = "Calculate power", description = "Returns a raised to the power of b")
    public ResponseEntity<CalculationResponse> power(@RequestBody CalculationRequest request) {
        double result = calculatorService.power(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculationResponse(result, "power"));
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Simple health check endpoint")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Calculator API is running!");
    }
}
