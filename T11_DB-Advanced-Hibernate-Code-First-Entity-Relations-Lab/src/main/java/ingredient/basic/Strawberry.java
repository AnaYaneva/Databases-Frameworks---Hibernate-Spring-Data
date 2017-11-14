package ingredient.basic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "ST")
public class Strawberry extends BasicIngredient {

    public static final String STRAWBERRY = "Strawberry";
    public static final BigDecimal PRICE = BigDecimal.valueOf(4.85);

    public Strawberry() {
        super(STRAWBERRY, PRICE);
    }
}
