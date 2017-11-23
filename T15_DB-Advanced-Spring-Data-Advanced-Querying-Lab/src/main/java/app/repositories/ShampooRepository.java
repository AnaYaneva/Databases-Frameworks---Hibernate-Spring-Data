package app.repositories;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Long>{

    List<BasicShampoo> findBySizeOrderByBrand(Size size);

    List<BasicShampoo> findBySizeOrLabelOrderByPrice(Size size, Label label);

    List<BasicShampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Long countByPriceLessThan(BigDecimal price);

    @Query("SELECT i.shampoos " +
            "FROM BasicIngredient AS i " +
            "WHERE i IN (:ingredients)")
    List<BasicShampoo> findByHavingIngredients(@Param(value = "ingredients") List<? extends Ingredient> ingredients);

    @Query(value = "SELECT s FROM BasicShampoo AS s JOIN s.ingredients AS i GROUP BY s.brand HAVING count(i.name) < :count")
    List<BasicShampoo> getShampoosByIngredientsCount(@Param("count") Long count);

    @Query(value = "SELECT sum(i.price) FROM BasicShampoo AS s JOIN s.ingredients AS i WHERE s.brand = :name")
    BigDecimal ingredientsPrice(@Param("name") String name);

}
