package ingredient.chemical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {

    public static final String AMMONIUM_CHLORIDE = "Ammonium Chloride";
    public static final BigDecimal PRICE = BigDecimal.valueOf(0.59);
    public static final String CHEMICAL_FORMULA = "NH4Cl";

    public AmmoniumChloride() {
        super(AMMONIUM_CHLORIDE, PRICE, CHEMICAL_FORMULA);
    }
}
