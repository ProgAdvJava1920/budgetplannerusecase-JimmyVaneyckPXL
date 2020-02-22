package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.FileHandler;

public class AccountMapper {
    private static final Logger LOGGER = LogManager.getLogger(AccountMapper.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
    private static List<Payment> payments;

    public static Account mapToAccount(String line) {

        Account account = new Account();

        try {
            payments = new ArrayList<>();
            String[] parameters = line.split(",");
            account.setName(parameters[0]);
            account.setIBAN(parameters[1]);
            addPayment(line);
            account.setPayments(payments);
        } catch (Exception e) {
            LOGGER.error("This is not a correct line.");
        }

        return account;
    }

    public static void addPayment(String line) {
        String[] parameters = line.split(",");
        LocalDateTime localDateTime = LocalDateTime.parse(parameters[3], FORMATTER);
        float amount = Float.parseFloat(parameters[4]);
        Payment payment = new Payment(localDateTime, amount, parameters[5], parameters[6]);
        payments.add(payment);
    }
}