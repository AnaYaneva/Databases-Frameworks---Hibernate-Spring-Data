package shampoo;

import label.BasicLabel;
import size.Size;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    public static final String FRESH_NUKE = "Fresh Nuke";
    public static final BigDecimal PRICE = BigDecimal.valueOf(9.33);
    public static final Size LARGE = Size.LARGE;

    public FreshNuke(BasicLabel label) {
        super(FRESH_NUKE, PRICE, LARGE, label);
    }

    public FreshNuke() {
    }
}
