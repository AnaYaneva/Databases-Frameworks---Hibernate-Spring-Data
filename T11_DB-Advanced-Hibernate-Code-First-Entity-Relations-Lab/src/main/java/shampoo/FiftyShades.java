package shampoo;

import label.BasicLabel;
import size.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShades extends BasicShampoo {
    public static final BigDecimal PRICE = BigDecimal.valueOf(6.69);
    public static final Size SMALL = Size.SMALL;
    public static final String BRAND = "Fifty Shades";

    public FiftyShades(BasicLabel label) {
        super(BRAND, PRICE, SMALL, label);
    }

    public FiftyShades() {
    }
}

