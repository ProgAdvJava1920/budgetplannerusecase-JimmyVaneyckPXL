package be.pxl.student.DAO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class PaymentDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private double amount;
    private String currency;
    private String detail;

    @ManyToOne
    private AccountDAO account;
    @ManyToOne
    private AccountDAO counterAccount;

    public PaymentDAO(){
    }

    public PaymentDAO(LocalDateTime date, double amount, String currency, String detail) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public int getId(){
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public AccountDAO getAccount() {
        return account;
    }

    public void setAccount(AccountDAO account) {
        this.account = account;
    }

    public AccountDAO getCounterAccount() {
        return counterAccount;
    }

    public void setCounterAccount(AccountDAO counterAccount) {
        this.counterAccount = counterAccount;
    }
}
