package ingredient.basic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "MI")
public class Mint extends BasicIngredient {

    public static final String MINT = "Mint";
    public static final BigDecimal PRICE = BigDecimal.valueOf(3.54);

    public Mint() {
        super(MINT, PRICE);
    }
}
