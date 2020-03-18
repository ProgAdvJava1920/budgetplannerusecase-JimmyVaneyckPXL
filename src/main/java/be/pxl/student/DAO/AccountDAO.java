package be.pxl.student.DAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "account")
public class AccountDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String IBAN;
    private String name;
    @OneToMany(mappedBy = "account")
    private List<PaymentDAO> payments = new ArrayList<>();

    public AccountDAO() {
    }

    public AccountDAO(String IBAN, String name){
        this.IBAN = IBAN;
        this.name = name;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", outgoing payments=[" + payments.stream().map(PaymentDAO::toString).collect(Collectors.joining(",")) + "]}";
    }

    public List<PaymentDAO> getPayments() {
        return payments;
    }

    public void addPayment(PaymentDAO payment) {
        payments.add(payment);
    }
}