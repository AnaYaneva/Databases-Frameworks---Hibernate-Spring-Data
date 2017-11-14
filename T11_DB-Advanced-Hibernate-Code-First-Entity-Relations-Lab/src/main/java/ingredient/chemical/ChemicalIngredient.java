package ingredient.chemical;

import ingredient.basic.Ingredient;

interface ChemicalIngredient extends Ingredient {

    void setChemicalFormula(String formula);

    String getChemicalFormula();
}
