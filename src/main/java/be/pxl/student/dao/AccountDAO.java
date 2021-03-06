package be.pxl.student.dao;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.entity.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAllAccounts();
    Account createAccount(Account account);
    boolean updateAccount(Account account, int id);
    boolean deleteAccount(int id);
    Account getAccountById(int id);
    Account getAccountByName(String name);

}
