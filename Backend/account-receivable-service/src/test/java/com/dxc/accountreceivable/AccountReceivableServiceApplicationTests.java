package com.dxc.accountreceivable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dxc.accountreceivable.feign.UserServiceFeignInterface;
import com.dxc.accountreceivable.model.AccountReceivable;
import com.dxc.accountreceivable.repository.AccountReceivableRepository;
import com.dxc.accountreceivable.service.AccountReceivableService;

@SpringBootTest
class AccountReceivableServiceApplicationTests {

	@Autowired
    private AccountReceivableService accountReceivableService;

    @MockBean
    private AccountReceivableRepository accountReceivableRepository;

    @MockBean
    private UserServiceFeignInterface userServiceFeignInterface;
    

    @Test
    void testCreateReceivable_Success() {
        
        when(userServiceFeignInterface.getDetailsByCompanyId(any(Integer.class)))
                .thenReturn(ResponseEntity.ok().build());

        AccountReceivable accountReceivable = createAccountReceivable();

        when(accountReceivableRepository.save(any(AccountReceivable.class)))
                .thenReturn(accountReceivable);

        ResponseEntity<String> responseEntity = accountReceivableService.createReceivable(accountReceivable);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Entry created!", responseEntity.getBody());
    }


    @Test
    void testGetAllAccountReceivable_Success() {
        List<AccountReceivable> accountReceivables = new ArrayList<>();
        accountReceivables.add(createAccountReceivable());

        when(accountReceivableRepository.findAll())
                .thenReturn(accountReceivables);

        ResponseEntity<List<AccountReceivable>> responseEntity = accountReceivableService.getAllAccountReceivable();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountReceivables, responseEntity.getBody());
    }

    @Test
    void testGetAllAccountReceivable_NoDataFound() {
        when(accountReceivableRepository.findAll())
                .thenReturn(new ArrayList<>());

        ResponseEntity<List<AccountReceivable>> responseEntity = accountReceivableService.getAllAccountReceivable();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(new ArrayList<>(), responseEntity.getBody());
    }


    @Test
    void testGetInvoiceById_Success() {
        AccountReceivable accountReceivable = createAccountReceivable();

        when(accountReceivableRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(accountReceivable));

        ResponseEntity<AccountReceivable> responseEntity = accountReceivableService.getInvoiceById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountReceivable, responseEntity.getBody());
    }

    @Test
    void testGetInvoiceById_NotFound() {
        when(accountReceivableRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        ResponseEntity<AccountReceivable> responseEntity = accountReceivableService.getInvoiceById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }


    private AccountReceivable createAccountReceivable() {
        return AccountReceivable.builder()
                .invoiceNumber("INV-001")
                .productDescription("Product ABC")
                .quantity(5)
                .price(100.0)
                .dueDate(LocalDate.now().plusDays(30))
                .amount(500.0)
                .status("Pending")
                .customerName("John Doe")
                .companyId(1)
                .build();
    }

}
