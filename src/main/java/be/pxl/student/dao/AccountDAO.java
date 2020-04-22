package be.pxl.student.dao;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.entity.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAccounts();
    Account createAccount(Account account);
    boolean updateAccount(AccountResource account, int id);
    boolean deleteAccount(int id);
    Account getAccountById(int id);

}
