package com.example.calculator.controller;

import com.example.calculator.dto.CalculationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Calculator Controller Integration Tests")
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Health endpoint should return OK")
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator API is running!"));
    }

    @Test
    @DisplayName("Addition endpoint should return correct result")
    void testAdditionEndpoint() throws Exception {
        // This test will FAIL because the service implementation is wrong
        CalculationRequest request = new CalculationRequest(10.0, 5.0);

        mockMvc.perform(post("/api/calculator/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15.0))
                .andExpect(jsonPath("$.operation").value("addition"));
    }

    @Test
    @DisplayName("Subtraction endpoint should return correct result")
    void testSubtractionEndpoint() throws Exception {
        // This test will FAIL because the service implementation is wrong
        CalculationRequest request = new CalculationRequest(20.0, 8.0);

        mockMvc.perform(post("/api/calculator/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(12.0))
                .andExpect(jsonPath("$.operation").value("subtraction"));
    }

    @Test
    @DisplayName("Multiplication endpoint should return correct result")
    void testMultiplicationEndpoint() throws Exception {
        // This test should PASS
        CalculationRequest request = new CalculationRequest(6.0, 7.0);

        mockMvc.perform(post("/api/calculator/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(42.0))
                .andExpect(jsonPath("$.operation").value("multiplication"));
    }

    @Test
    @DisplayName("Division endpoint should return correct result")
    void testDivisionEndpoint() throws Exception {
        // This test should PASS for normal division
        CalculationRequest request = new CalculationRequest(100.0, 4.0);

        mockMvc.perform(post("/api/calculator/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(25.0))
                .andExpect(jsonPath("$.operation").value("division"));
    }

    @Test
    @DisplayName("Power endpoint should return correct result")
    void testPowerEndpoint() throws Exception {
        // This test should PASS
        CalculationRequest request = new CalculationRequest(3.0, 4.0);

        mockMvc.perform(post("/api/calculator/power")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(81.0))
                .andExpect(jsonPath("$.operation").value("power"));
    }

    @Test
    @DisplayName("Division by zero should be handled properly")
    void testDivisionByZeroEndpoint() throws Exception {
        // This test will FAIL because division by zero is not properly handled
        CalculationRequest request = new CalculationRequest(10.0, 0.0);

        // We expect either a 400 Bad Request or proper error handling
        // Currently this will fail because the service returns Infinity
        mockMvc.perform(post("/api/calculator/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }
}
