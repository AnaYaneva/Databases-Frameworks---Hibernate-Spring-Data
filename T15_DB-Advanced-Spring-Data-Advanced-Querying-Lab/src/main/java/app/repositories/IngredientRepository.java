package app.repositories;

import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<BasicIngredient> findByNameIn(List<String> names);

    List<BasicIngredient> findByNameStartsWith(String str);

    @Transactional
    void deleteByName(String name);

    //1th task
    @Query("UPDATE BasicIngredient b set b.price = b.price * :number ")
    @Modifying
    @Transactional
    void increaseIngredientPrice(@Param(value = "number") BigDecimal number);


    //12th task
    @Query("UPDATE BasicIngredient b set b.price = b.price * :number where b.name IN :names")
    @Modifying
    @Transactional
    void increaseIngredientPriceByName(@Param(value = "names") List<String> names, @Param(value = "number") BigDecimal number);

}
