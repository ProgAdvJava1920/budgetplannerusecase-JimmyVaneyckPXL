package be.pxl.student;

import be.pxl.student.DAO.AccountDAO;
import be.pxl.student.DAO.AccountMapperDAO;
import be.pxl.student.DAO.PaymentDAO;
import be.pxl.student.DAO.PaymentMapperDAO;
import be.pxl.student.util.AccountMapper;
import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.List;

public class BudgetPlanner {
    private static final Logger LOGGER = LogManager.getLogger(AccountMapper.class);
    private static Path path = Path.of("src/main/resources/account_payments.csv");

    public static void main(String[] args) {
        /*for (int i = 0; i < 25; i++) {
            LOGGER.info("Start reading");
            new BudgetPlannerImporter().importCsv(path);
            LOGGER.info("Finsih reading");
        }*/

        PaymentMapperDAO mapper = new PaymentMapperDAO();
        mapper.createPayment();
        //List<AccountDAO> accounts = mapper.getAllAccounts();

        /*for (AccountDAO account: accounts) {
            System.out.println(account);
        }*/
    }
}
