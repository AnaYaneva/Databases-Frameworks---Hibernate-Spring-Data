package shampoo;

import batch.ProductionBatch;
import ingredient.basic.BasicIngredient;
import label.BasicLabel;
import size.Size;

import java.math.BigDecimal;
import java.util.Set;

 interface Shampoo {
     long getId();

     void setId(long id);

     String getBrand();

     void setBrand(String brand);

     BasicLabel getLabel();

     void setLabel(BasicLabel label);

     //ProductionBatch getBatch();

     //void setBatch(ProductionBatch batch);

     BigDecimal getPrice();

     void setPrice(BigDecimal price);

     Size getSize();

     void setSize(Size size);

     Set<BasicIngredient> getIngredients();

     void setIngredients(Set<BasicIngredient> ingredients);

 }
