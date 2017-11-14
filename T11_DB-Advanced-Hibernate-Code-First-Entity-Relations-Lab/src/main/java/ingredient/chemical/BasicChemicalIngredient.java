package ingredient.chemical;

import ingredient.basic.BasicIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient
        extends BasicIngredient
        implements ChemicalIngredient {

    @Column(name="chemical_formula")
    private String ChemicalFormula;

    public BasicChemicalIngredient() {
    }

    public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        ChemicalFormula = chemicalFormula;
    }

    public void setChemicalFormula(String formula) {

    }

    public String getChemicalFormula() {
        return null;
    }
}
