package com.example.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CalculationRequest {

    @Schema(description = "First operand", example = "10.5")
    private double a;

    @Schema(description = "Second operand", example = "5.2")
    private double b;

    public CalculationRequest() {
    }

    public CalculationRequest(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
