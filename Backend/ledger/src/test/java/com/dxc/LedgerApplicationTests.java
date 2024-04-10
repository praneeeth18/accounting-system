package com.dxc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dxc.service.LedgerServiceImpl;

@SpringBootTest
class LedgerApplicationTests {
	
	private final LedgerServiceImpl ledgerService;

    public LedgerApplicationTests(LedgerServiceImpl ledgerService) {
        this.ledgerService = ledgerService;
    }

    @Test
    void testContextLoads() {
        assertNotNull(ledgerService); // Ensure that the ledgerService bean is successfully injected
    }
}
