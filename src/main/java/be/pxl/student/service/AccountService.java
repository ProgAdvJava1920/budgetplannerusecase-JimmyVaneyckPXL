package be.pxl.student.service;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.dao.AccountDAO;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.entity.Account;

public class AccountService {
    private AccountDaoImpl accountDAO;

    public AccountService() {
        accountDAO = new AccountDaoImpl("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "test");
    }

    public void createAccount(AccountResource accountResource) throws Exception {
        Account account = new Account(accountResource.getIBAN(), accountResource.getName());

        if (accountDAO.findIbanOrNameInAccounts(accountResource)) {
            int id = accountDAO.ibanInAccountButEmptyName(accountResource);

            if (id != -1) {
                accountDAO.updateAccount(accountResource, id);
            } else {
                throw new Exception("Account bestaat al!");
            }
        } else {
            accountDAO.createAccount(account);
        }
    }

    public Account getAccountByName(String name) throws Exception {
        Account account = accountDAO.getAccountByName(name);
        if (account == null) {
            throw new Exception("Account bestaat niet!");
        } else {
            return account;
        }
    }
}
