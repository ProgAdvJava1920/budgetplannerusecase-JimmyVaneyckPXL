package be.pxl.student.service;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.dao.AccountDAO;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.util.List;

public class AccountService {
    private AccountDaoImpl accountDAO;

    public AccountService() {
        accountDAO = new AccountDaoImpl("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "test");
    }

    public void createAccount(Account account) throws Exception {
        if (accountDAO.findIbanOrNameInAccounts(account)) {
            int id = accountDAO.ibanInAccountButEmptyName(account);

            if (id != -1) {
                accountDAO.updateAccount(account, id);
            } else {
                throw new Exception("Account bestaat al!");
            }
        } else {
            accountDAO.createAccount(account);
        }
    }

    public List<Account> getAllAccounts() throws Exception {
        return accountDAO.getAccounts();
    }

    public Account getAccountByName(String name) throws Exception {
        Account account = accountDAO.getAccountByName(name);
        if (account == null) {
            throw new Exception("Account bestaat niet!");
        } else {
            return account;
        }
    }

    public Account getAccountById(int id) throws Exception {
        Account account = accountDAO.getAccountById(id);
        if (account == null) {
            throw new Exception("Account bestaat niet!");
        } else {
            return account;
        }
    }
}
