package shampoo;

import label.BasicLabel;
import size.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {
    public static final String PINK_PANTHER = "Pink Panther";
    public static final BigDecimal PRICE = BigDecimal.valueOf(8.50);
    public static final Size MEDIUM = Size.MEDIUM;

    public PinkPanther(BasicLabel label) {
        super(PINK_PANTHER, PRICE, MEDIUM, label);
    }

    public PinkPanther() {
    }
}
