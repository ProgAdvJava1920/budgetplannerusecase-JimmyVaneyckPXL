package be.pxl.student.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private int id;
    private LocalDateTime date;
    private float amount;
    private String currency;
    private String detail;
    @Transient
    private int accountId;
    @ManyToOne
    private Account account;
    //needed for jdbc
    @Transient
    private String counterAccountString;
    @Transient
    private int counterAccountId;
    @ManyToOne
    private Account counterAccount;

    public Payment(LocalDateTime date, float amount, String currency, String detail) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
    }

    public Payment() { }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getCounterAccount() {
        return counterAccount;
    }

    public void setCounterAccount(Account counterAccount) {
        this.counterAccount = counterAccount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCounterAccountString() {
        return counterAccountString;
    }

    public void setCounterAccountString(String counterAccount) {
        this.counterAccountString = counterAccount;
    }

    public int getCounterAccountId() {
        return counterAccountId;
    }

    public void setCounterAccountId(int counterAccountId) {
        this.counterAccountId = counterAccountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                ", accountId=" + accountId +
                ", account=" + account +
                ", counterAccount=" + counterAccount +
                '}';
    }
}