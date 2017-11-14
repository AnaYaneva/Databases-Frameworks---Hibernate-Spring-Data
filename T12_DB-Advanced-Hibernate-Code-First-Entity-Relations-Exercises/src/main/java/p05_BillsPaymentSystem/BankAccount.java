package p05_BillsPaymentSystem;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="p5_bank_account")
public class BankAccount extends BasicBillingDetail {
    private String bankName;
    private String swiftCode;

    public BankAccount() {
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
