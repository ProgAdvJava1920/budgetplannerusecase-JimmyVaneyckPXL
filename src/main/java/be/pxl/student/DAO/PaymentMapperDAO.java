package be.pxl.student.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class PaymentMapperDAO {
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    public PaymentMapperDAO() {
        //entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
        //entityManager = entityManagerFactory.createEntityManager();
    }

    public void createPayment() {

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            AccountDAO account = new AccountDAO("BE43434" , "jimmy");
            AccountDAO account2 = new AccountDAO("BE43434" , "akthy");
            PaymentDAO payment = new PaymentDAO(LocalDateTime.now(), 13, "euro", "geen");
            account.addPayment(payment);
            payment.setAccount(account);
            payment.setCounterAccount(account2);
            entityManager.persist(payment);
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
}
