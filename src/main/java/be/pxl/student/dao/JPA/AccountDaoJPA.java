package be.pxl.student.dao.JPA;

import be.pxl.student.dao.AccountDAO;
import be.pxl.student.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDaoJPA implements AccountDAO {
    private EntityManager entityManager;

    //private static final Logger LOGGER = LogManager.getLogger(AccountDao.class);

    public AccountDaoJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Account createAccount(Account account) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
        return account;
    }

    @Override
    public boolean updateAccount(Account account, int id) {
        account.setId(id);
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleteAccount(int id) {
        entityManager.getTransaction().begin();
        Account attachedAccount = entityManager.find(Account.class, id);
        entityManager.remove(attachedAccount);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Account getAccountById(int id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public Account getAccountByName(String name) {
        return entityManager.find(Account.class, name);
    }

    public List<Account> getAllAccounts() {
        TypedQuery<Account> query = entityManager.createNamedQuery("account.getAll", Account.class);
        return query.getResultList();
    }
}
