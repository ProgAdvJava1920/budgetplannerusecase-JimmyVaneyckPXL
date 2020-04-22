package be.pxl.student;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountServiceGetAccountByNameTest {
    private static final long USER_ID = 5l;

    @Mock
    private AccountDaoImpl accountDAO;
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
        assertThrows(Exception.class, () -> accountService.getAccountByName("Wrong name"));
    }

    @Test
    public void WhenNameIsFoundItWillReturnTrue() throws Exception {
        when(accountDAO.getAccountByName("Jimmy")).thenReturn(account);
        assertEquals(account, accountService.getAccountByName("Jimmy"));
    }
}
