package be.pxl.student;

import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.dao.impl.PaymentDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BudgetPlannerDAO {
    public static void main(String[] args) {
        AccountDaoImpl accountMapper = new AccountDaoImpl("jdbc:mysql://localhost:3306/musicdb?useSSL=false", "user", "test");
        PaymentDaoImpl paymentMapper = new PaymentDaoImpl("jdbc:mysql://localhost:3306/musicdb?useSSL=false", "user", "test");

        List<Account> accounts = accountMapper.getAccounts();
        List<Payment> payments = paymentMapper.getPayments();

        for (Payment payment: payments) {
            int accountId = payment.getAccountId();
            int counterAccountId = payment.getCounterAccountId();

            accounts.stream().filter(a -> a.getId() == accountId).findFirst().get().addPayment(payment);
            accounts.stream().filter(a -> a.getId() == counterAccountId).findFirst().get().addCounterPayment(payment);
            payment.setCounterAccountString(accounts.stream().filter(a -> a.getId() == counterAccountId).findFirst().get().getIBAN());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name(x to stop): ");
        String name = scanner.nextLine();

        while (!name.toLowerCase().equals("x")) {
            try {
                String finalName = name;
                Account account = accounts.stream().filter(a -> a.getName().toLowerCase().equals(finalName.toLowerCase())).findFirst().get();
                System.out.println("Payments: ");
                for (Payment payment:account.getPayments()) {
                    System.out.println(payment);
                }

                System.out.println("Counter Payments: ");
                for (Payment payment : account.getCounterPayments()) {
                    System.out.println(payment);
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }

            System.out.println("Enter name(x to stop): ");
            name = scanner.nextLine();
        }
    }

}