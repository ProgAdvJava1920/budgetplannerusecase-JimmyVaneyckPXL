package be.pxl.student.REST.resource;

import javax.persistence.Transient;
import java.time.LocalDateTime;

public class PaymentResource {
    private LocalDateTime date;
    private float amount;
    private String detail;
    private String counterAccount;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCounterAccount(String counterAccount) {
        this.counterAccount = counterAccount;
    }

    public String getCounterAccount() {
        return counterAccount;
    }
}
