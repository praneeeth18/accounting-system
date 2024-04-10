package com.payable;

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

import com.payable.dao.AccountPayableDao;
import com.payable.feign.UserServiceFeingInterface;
import com.payable.model.AccountPayable;
import com.payable.service.AccountPayableService;

@SpringBootTest

class AccountsPayableApplicationTests {

	@Autowired
    private AccountPayableService accountPayableService;

    @MockBean
    private AccountPayableDao accountPayableDao;

    @MockBean
    private UserServiceFeingInterface userServiceFeingInterface;
    

    @Test
    void testCreatePayable_Success() {
        
        when(userServiceFeingInterface.getDetailsByCompanyId(any(Integer.class)))
                .thenReturn(ResponseEntity.ok().build());

        AccountPayable accountPayable = createAccountPayable();

        when(accountPayableDao.save(any(AccountPayable.class)))
                .thenReturn(accountPayable);

        ResponseEntity<String> responseEntity = accountPayableService.createPayable(accountPayable);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Entry created!", responseEntity.getBody());
    }


    @Test
    void testGetAllAccountPayable_Success() {
        List<AccountPayable> accountPayable = new ArrayList<>();
        accountPayable.add(createAccountPayable());

        when(accountPayableDao.findAll())
                .thenReturn(accountPayable);

        ResponseEntity<List<AccountPayable>> responseEntity = accountPayableService.getAllAccountPayable();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountPayable, responseEntity.getBody());
    }

    @Test
    void testGetAllAccountPayable_NoDataFound() {
        when(accountPayableDao.findAll())
                .thenReturn(new ArrayList<>());

        ResponseEntity<List<AccountPayable>> responseEntity = accountPayableService.getAllAccountPayable();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(new ArrayList<>(), responseEntity.getBody());
    }


    @Test
    void testGetInvoiceById_Success() {
        AccountPayable accountPayable = createAccountPayable();

        when(accountPayableDao.findById(any(Long.class)))
                .thenReturn(Optional.of(accountPayable));

        ResponseEntity<AccountPayable> responseEntity = accountPayableService.getInvoiceById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountPayable, responseEntity.getBody());
    }

    @Test
    void testGetInvoiceById_NotFound() {
        when(accountPayableDao.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        ResponseEntity<AccountPayable> responseEntity = accountPayableService.getInvoiceById(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }


    private AccountPayable createAccountPayable() {
        return AccountPayable.builder()
                .invoiceNumber("INV-001")
                .productDescription("Product ABC")
                .quantity(5)
                .price(100.0)
                .dueDate(LocalDate.now().plusDays(30))
                .totalAmount(500.0)
                .status("Pending")
                .vendorName("John Doe")
                .companyId(1)
                .build();
    }


}
