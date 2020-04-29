package be.pxl.student.service;

import be.pxl.student.dao.AccountDAO;
import be.pxl.student.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AccountServiceGetAllAccountsTest {
    private static final long USER_ID = 5l;

    @Mock
    private AccountDAO accountDAO;
    @InjectMocks
    private AccountService accountService;
    private List<Account> accounts;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        accounts = new ArrayList<>();
        accounts.add(new Account("BE3252352", "Jimmy"));
        accounts.add(new Account("BE9393939", "Kathy"));
    }

    @Test
    public void WhenThereAreAccountsItWillReturnAllAccounts() throws Exception {
        when(accountDAO.getAllAccounts()).thenReturn(accounts);

        List<Account> retrievedAccounts = accountService.getAllAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            assertEquals(accounts.get(i).getName(), retrievedAccounts.get(i).getName());
            assertEquals(accounts.get(i).getIBAN(), retrievedAccounts.get(i).getIBAN());
        }
    }
}
