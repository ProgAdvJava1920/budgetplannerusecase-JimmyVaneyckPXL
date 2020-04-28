package be.pxl.student.dao.impl;

import be.pxl.student.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountDaoImplTests {
    private AccountDaoImpl accountDao = new AccountDaoImpl("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "test");

    @Test
    public void userCanGetAccountById() {
        Account account = new Account();
        account.setIBAN("BE16393939");
        account.setName("Naam");
        int accountId = accountDao.createAccount(account).getId();
        Account retrievedAccount = accountDao.getAccountById(accountId);

        assertNotNull(retrievedAccount);
        assertEquals(retrievedAccount.getIBAN(), account.getIBAN());
        assertEquals(retrievedAccount.getName(), account.getName());
    }

    @Test
    public void userCanGetAccountByName() {
        Account account = new Account();
        account.setIBAN("BE16393939");
        account.setName("Naam");
        String accountName = accountDao.createAccount(account).getName();
        Account retrievedAccount = accountDao.getAccountByName(accountName);

        assertNotNull(retrievedAccount);
        assertEquals(retrievedAccount.getIBAN(), account.getIBAN());
        assertEquals(retrievedAccount.getName(), account.getName());
    }

    @Test
    public void userCanGetAccountByIban() {
        Account account = new Account();
        account.setIBAN("BE16393939");
        account.setName("Naam");
        String accountIban = accountDao.createAccount(account).getName();
        Account retrievedAccount = accountDao.getAccountByIBAN(accountIban);

        assertNotNull(retrievedAccount);
        assertEquals(retrievedAccount.getIBAN(), account.getIBAN());
        assertEquals(retrievedAccount.getName(), account.getName());
    }
}
