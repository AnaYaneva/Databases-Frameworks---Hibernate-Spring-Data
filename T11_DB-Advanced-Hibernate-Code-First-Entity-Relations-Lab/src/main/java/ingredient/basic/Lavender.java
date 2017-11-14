package ingredient.basic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "LV")
public class Lavender extends BasicIngredient {

    public static final String LAVENDER = "Lavender";
    public static final BigDecimal PRICE = BigDecimal.valueOf(2);

    public Lavender() {
        super(LAVENDER, PRICE);
    }
}
