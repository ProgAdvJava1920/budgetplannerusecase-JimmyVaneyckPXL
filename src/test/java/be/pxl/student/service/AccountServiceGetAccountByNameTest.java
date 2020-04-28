package be.pxl.student.service;

import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.dao.AccountDAO;
import be.pxl.student.dao.LabelDao;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Label;
import be.pxl.student.service.AccountService;
import be.pxl.student.service.LabelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceGetAccountByNameTest {
    private static final long USER_ID = 5l;

    @Mock
    private AccountDAO accountDAO;
    @InjectMocks
    private AccountService accountService;
    private Account account;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        account = new Account("BE3252352", "Jimmy");
    }

    @Test
    public void anExceptionIsThrownWhenNameIsNotFound() throws Exception {
        account.setName("Jos");
        assertThrows(Exception.class, () -> accountService.getAccountByName(account.getName()));
    }

    @Test
    public void WhenNameIsFoundItWillReturnAnAccount() throws Exception {
        when(accountDAO.getAccountByName(account.getName())).thenReturn(account);

        assertEquals(account.getName(), accountService.getAccountByName(account.getName()).getName());
        assertEquals(account.getIBAN(), accountService.getAccountByName(account.getName()).getIBAN());
    }
}
