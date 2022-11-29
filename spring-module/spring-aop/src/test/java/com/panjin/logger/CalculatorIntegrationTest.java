package com.panjin.logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:springAop-applicationContext.xml"})
public class CalculatorIntegrationTest {

    @Autowired
    private SampleAdder sampleAdder;

    @Test
    public void whenAddValidValues_returnsSucessfully() {
        final int addedValue = sampleAdder.add(12, 12);

        assertEquals(24, addedValue);
    }

    @Test
    public void whenAddInValidValues_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    sampleAdder.add(12, -12);
                }
        );
        assertEquals("Make sure all the arguments are greater than zero.", exception.getMessage());
    }
}
