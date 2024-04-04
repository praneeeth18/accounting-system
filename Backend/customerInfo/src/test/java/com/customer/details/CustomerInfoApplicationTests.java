package com.customer.details;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerInfoApplicationTests {

    @Test
    void testMethodRenamed() {
        int expected = 1;
        int actual = 1;
        assertEquals(expected, actual, "1 should be equal to 1");
    }

}
