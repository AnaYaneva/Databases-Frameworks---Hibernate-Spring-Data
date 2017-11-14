package p05_BillsPaymentSystem;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="p5_credit_cards")
public class CreditCard extends BasicBillingDetail {
    private String cardType;
    private Date expDate;

    public CreditCard() {
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getExpDate() {
        return this.expDate;
    }

    public void setExpDate(Date expMonth) {
        this.expDate = expMonth;
    }
}
