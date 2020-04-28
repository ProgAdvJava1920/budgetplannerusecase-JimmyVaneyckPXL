package be.pxl.student.service;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.REST.resource.PaymentResource;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.dao.impl.PaymentDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentService {
    private PaymentDaoImpl paymentDao;
    private AccountDaoImpl accountDao;

    public PaymentService() {
        paymentDao = new PaymentDaoImpl("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "test");
        accountDao = new AccountDaoImpl("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "test");
    }

    public void createPayment(PaymentResource paymentResource, String accountName) throws Exception {
        Payment payment;
        Account account = accountDao.getAccountByName(accountName);
        Account counterAccount = new Account();

        //check if account exist
        if (account == null) {
            throw new Exception("Account bestaat niet!");
        }

        //check if counterIban exist in database
        counterAccount.setIBAN(paymentResource.getCounterAccount());
        counterAccount.setName("");
        if (!accountDao.findIbanOrNameInAccounts(counterAccount)) {
            int id = accountDao.createAccount(counterAccount).getId();
            counterAccount.setId(id);
        }  else {
            counterAccount = accountDao.getAccountByIBAN(counterAccount.getIBAN());
        }

        payment = new Payment(LocalDateTime.now(), paymentResource.getAmount(), "Euro", paymentResource.getDetail());
        paymentDao.createPayment(payment, account, counterAccount);
    }

    public List<Payment> getAllPayments(String accountName) throws Exception {
        Account account = accountDao.getAccountByName(accountName);

        if (account == null) {
            throw new Exception("Account bestaat niet!");
        }

        return paymentDao.getPaymentsByAccountId(account.getId());
    }

    private Account mapToAccount(AccountResource accountResource) {
        return new Account(accountResource.getIBAN(), accountResource.getName());
    }
}
