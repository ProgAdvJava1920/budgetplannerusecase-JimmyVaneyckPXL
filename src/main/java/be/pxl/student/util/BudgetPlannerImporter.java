package be.pxl.student.util;

import be.pxl.student.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private List<Account> accounts = new ArrayList<>();

    public void importCsv(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                Account account = AccountMapper.mapToAccount(line);
                LOGGER.fatal(account);
                accounts.add(account);
            }
        } catch (IOException e) {
            LOGGER.error("invalid file .csv expected!");
        }
    }
}
