package be.pxl.student.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AccountMapperDAO {
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    public AccountMapperDAO() {
        //entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
        //entityManager = entityManagerFactory.createEntityManager();
    }

    public void createAccount() {

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            AccountDAO account = new AccountDAO("BE453545", "jimmy");
            entityManager.persist(account);
            transaction.commit();
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }

    public List<AccountDAO> getAllAccounts() {
        List<AccountDAO> accounts = new ArrayList<>();
        entityManager = null;
        entityManagerFactory = null;

        entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println("hoi");
        try {
            entityManager.createQuery("SELECT a FROM AccountDAO a", AccountDAO.class).getResultStream().forEach(a -> accounts.add(a));
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }

        return accounts;
    }
}
