package com.example.demo.controller;

import com.example.demo.dto.CalculatorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {
    @InjectMocks
    private CalculatorController calculatorController;

    static Double num1;
    static Double num2;
    static Double num3;


    @BeforeAll
    static void beforeAll(){
        num1 = 3.5;
        num2 = 3.5;
        num3 = 3.5;
    }

    @Test
    @DisplayName("Test Addition")
    void testAddFunction_Success(){
        Double result = calculatorController.add(num1, num2, num3);
        assertEquals(7.0, result);
    }

    @Test
    @DisplayName("Test Addition Failure Scenario")
    void testAddFunction_Failure(){
        Double result = calculatorController.add(num1 - 0.5, num2, num3);
        Assertions.assertNotEquals(7.0, result);
    }

    @Test
    @DisplayName("Test Substraction")
    public void testSubFunction_num1_gt_num2(){
        Double result = calculatorController.substract(num1+1, 2+num2);
        assertEquals(0.0, result);
    }

    @Test
    @DisplayName("Test Multiplication")
    void testMultiply() {

        CalculatorDTO calculatorDTO = new CalculatorDTO();
        calculatorDTO.setNum1(num1);
        calculatorDTO.setNum2(num2);
        calculatorDTO.setNum3(num3);
        calculatorDTO.setNum4(2.0);

        ResponseEntity<Double> responseEntity = calculatorController.multiply(calculatorDTO);
        assertEquals(85.75, responseEntity.getBody());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue(), "Expecting the status as OK");

    }
}
