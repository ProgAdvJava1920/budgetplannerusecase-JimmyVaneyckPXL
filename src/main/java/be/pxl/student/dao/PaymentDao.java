package be.pxl.student.dao;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment> getPayments();
    List<Payment> getPaymentsByAccountId(int accountId);
}
