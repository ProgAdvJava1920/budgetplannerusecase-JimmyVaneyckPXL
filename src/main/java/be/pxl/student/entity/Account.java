package be.pxl.student.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name="account.getAll", query = "SELECT a FROM Account as a")
@Table(name = "account")
public class Account {
    @Id
    private long id;
    private String IBAN;
    private String name;
    @OneToMany
    private List<Payment> payments = new ArrayList<>();
    @OneToMany
    private List<Payment> counterPayments = new ArrayList<>();

    public Account() {
    }

    public Account(String IBAN, String name){
        this.IBAN = IBAN;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setCounterPayments(List<Payment> counterPayments) {
        this.counterPayments = counterPayments;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

    public void addCounterPayment(Payment payment) {
        float amount = payment.getAmount();

        if(amount < 0) {
            amount = Math.abs(amount);
        } else {
            amount -= amount;
        }

        Payment counterPayment = new Payment(payment.getDate(), amount, payment.getCurrency(), payment.getDetail());
        counterPayment.setCounterAccountString(payment.getCounterAccountString());
        counterPayment.setCounterAccountId(payment.getCounterAccountId());
        payment.setAccountId(payment.getAccountId());
        payment.setId(payment.getId());
        this.counterPayments.add(counterPayment);
    }

    public int getId() {
        return (int) id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getName() {
        return name;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Payment> getCounterPayments() {
        return counterPayments;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", payments=" + payments +
                ", counterPayments=" + counterPayments +
                '}';
    }
}