package be.pxl.student.service;

import be.pxl.student.dao.JPA.AccountDaoJPA;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AccountServiceJPA {
    private AccountDaoJPA accountDAO;

    public AccountServiceJPA() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("budgetplanner_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        accountDAO = new AccountDaoJPA(entityManager);
    }

    public List<Account> getAllAccounts() throws Exception {
        return accountDAO.getAllAccounts();
    }
}
