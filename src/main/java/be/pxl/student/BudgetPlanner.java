package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;

import java.nio.file.Path;

public class BudgetPlanner {
    public static void main(String[] args) {
        Path path = Path.of("src/main/resources/account_payments.csv");
        new BudgetPlannerImporter().importCsv(path);
    }
}
