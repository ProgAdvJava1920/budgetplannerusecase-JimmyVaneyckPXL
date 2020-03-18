package be.pxl.student.util;

import be.pxl.student.DAO.PaymentDAO;
import be.pxl.student.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private List<Account> accounts = new ArrayList<>();

    public void importCsv(Path path,  EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        HashMap<String, Account> addedAccounts = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            transaction.begin();

            while ((line = reader.readLine()) != null) {
                Account account = null;

                try {
                    account = AccountMapper.mapToAccount(line);
                } catch (InvalidPaymentException e) {
                    LOGGER.fatal("An error occurred while reading file: {}", path);
                }

                LOGGER.fatal(account);
                accounts.add(account);
            }
        } catch (IOException e) {
            LOGGER.fatal("invalid file .csv expected!");
        }
    }
}
