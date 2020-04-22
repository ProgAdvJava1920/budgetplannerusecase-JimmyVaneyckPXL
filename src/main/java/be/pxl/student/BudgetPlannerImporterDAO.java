package be.pxl.student;

import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.dao.impl.PaymentDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

public class BudgetPlannerImporterDAO {
    public static void main(String[] args) {
        List<Account> accounts;

        try {
            BudgetPlannerImporter budgetPlannerImporter = new BudgetPlannerImporter();

            accounts = budgetPlannerImporter.importCsv(Path.of("src/main/resources/account_payments.csv"));
            AccountDaoImpl accountMapper = new AccountDaoImpl("jdbc:mysql://localhost:3306/musicdb?useSSL=false", "user", "test");
            PaymentDaoImpl paymentMapper = new PaymentDaoImpl("jdbc:mysql://localhost:3306/musicdb?useSSL=false", "user", "test");
            LogManager.getLogger().debug(accounts.size());

            for (Account account : accounts) {
                account = accountMapper.createAccount(account);
            }

            /*for (Account account: accounts) {
                for (Payment payment:account.getPayments()) {
                    try {
                        Account counterAccount = accounts.stream().filter(r -> r.getIBAN().equals(payment.getCounterAccountString())).findFirst().get();
                        LogManager.getLogger().debug(counterAccount);
                        paymentMapper.createPayment(payment, account, counterAccount);
                    } catch (NoSuchElementException e) {
                        System.out.println("CounterAccount does not exist");
                    }
                }
            }*/
        } catch (Exception e) {
            LogManager.getLogger().error(e);
        }
    }
}