package be.pxl.student.REST.resource;

public class AccountResource {
    private String IBAN;
    private String name;

    public String getIBAN() {
        return IBAN;
    }

    public String getName() {
        return name;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setName(String name) {
        this.name = name;
    }
}
