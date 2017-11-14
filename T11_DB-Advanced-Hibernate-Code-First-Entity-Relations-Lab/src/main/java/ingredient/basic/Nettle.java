package ingredient.basic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "NT")
public class Nettle extends BasicIngredient {

    public static final String NETTLE = "Nettle";
    public static final BigDecimal PRICE = BigDecimal.valueOf(6.12);

    public Nettle( ) {
        super(NETTLE, PRICE);
    }
}
