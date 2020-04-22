package be.pxl.student;

import be.pxl.student.util.AccountMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public class BudgetPlanner {
    private static final Logger LOGGER = LogManager.getLogger(AccountMapper.class);
    private static Path path = Path.of("src/main/resources/account_payments.csv");

    public static void main(String[] args) {
        /*for (int i = 0; i < 25; i++) {
            LOGGER.info("Start reading");
            new BudgetPlannerImporter().importCsv(path);
            LOGGER.info("Finsih reading");
        }*/


        //List<AccountDAO> accounts = mapper.getAllAccounts();

        /*for (AccountDAO account: accounts) {
            System.out.println(account);
        }*/
    }
}
