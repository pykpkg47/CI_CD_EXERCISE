package com.example.calculator.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CalculationResponse {

    @Schema(description = "The result of the calculation", example = "15.7")
    private double result;

    @Schema(description = "The operation performed", example = "addition")
    private String operation;

    public CalculationResponse() {
    }

    public CalculationResponse(double result, String operation) {
        this.result = result;
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
