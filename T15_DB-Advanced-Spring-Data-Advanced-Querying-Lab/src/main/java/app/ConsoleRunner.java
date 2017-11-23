package app;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.labels.Label;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import app.repositories.IngredientRepository;
import app.repositories.LabelRepository;
import app.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private ShampooRepository shampooRepository;
    private IngredientRepository ingredientRepository;
    private LabelRepository labelRepository;

    @Autowired
    public ConsoleRunner(ShampooRepository shampooRepository, IngredientRepository ingredientRepository, LabelRepository labelRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        solveProblem(8);
    }

    private void solveProblem(int i) {
        switch (i){
            case 1:
                p01();
                break;
            case 2:
                p02();
                break;
            case 3:
                p03();
                break;
            case 4:
                p04();
                break;
            case 5:
                p05();
                break;
            case 6:
                p06();
                break;
            case 7:
                p07();
                break;
            case 8:
                p08();
                break;
            case 9:
                p09();
                break;
            case 10:
                p10();
                break;
            case 11:
                p11();
                break;
            case 12:
                p12();
                break;
        }
    }

    private void p01() {
        List<BasicShampoo> bySize =
                this.shampooRepository.findBySizeOrderByBrand(Size.MEDIUM);
        for (Shampoo shampoo : bySize) {
            System.out.println(shampoo.getBrand()+" "+ shampoo.getSize().name()+" "+shampoo.getPrice()+" lv.");
        }
    }

    private void p02() {
        Label label10= this.labelRepository.findOne(10L);
        List<BasicShampoo> bySizeOrLabelOrderByPrice = this.shampooRepository.findBySizeOrLabelOrderByPrice(Size.MEDIUM, label10);
        for (Shampoo shampoo : bySizeOrLabelOrderByPrice) {
            System.out.println(shampoo.getBrand()+" "+ shampoo.getSize().name()+" "+shampoo.getPrice()+" lv.");
        }
    }

    private void p03() {
        List<BasicShampoo> byPriceHigherThanOrderByPriceDesc = this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(new BigDecimal(5));

        for (Shampoo shampoo : byPriceHigherThanOrderByPriceDesc) {
            System.out.println(shampoo.getBrand()+" "+ shampoo.getSize().name()+" "+shampoo.getPrice()+" lv.");
        }
    }

    private void p04() {
        List<BasicIngredient> byNameStartsWith = this.ingredientRepository.findByNameStartsWith("M");
        for (BasicIngredient basicIngredient : byNameStartsWith) {
            System.out.println(basicIngredient.getName());
        }
    }

    private void p05() {
        List<String> names = new ArrayList<>();
        names.add("Lavender");
        names.add("Herbs");
        names.add("Apple");

        List<BasicIngredient> byNameIn = this.ingredientRepository.findByNameIn(names);

        for (BasicIngredient basicIngredient : byNameIn) {
            System.out.println(basicIngredient.getName());
        }
    }

    private void p06() {
        System.out.println(
                this.shampooRepository.countByPriceLessThan(new BigDecimal(8.50)));
    }

    private void p07() {
        BasicIngredient berry = this.ingredientRepository.findOne(10L);
        BasicIngredient mc = this.ingredientRepository.findOne(11L);
        List<BasicIngredient> ingredients=new ArrayList<>();
        ingredients.add(berry);
        ingredients.add(mc);
        List<BasicShampoo> byIngredients =
                this.shampooRepository.findByHavingIngredients(ingredients);
        for (Shampoo shampoo : byIngredients) {
            System.out.println(shampoo.getBrand());
        }
    }

    private void p08() {
        List<BasicShampoo> byIngredientsCount = this.shampooRepository.getShampoosByIngredientsCount(2L);
        for (BasicShampoo s : byIngredientsCount) {
            System.out.println(s.getBrand());
        }
    }

    private void p09() {
        BigDecimal price = this.shampooRepository.ingredientsPrice("Fresh it up!");
        System.out.println(price);
    }

    private void p10() {
        this.ingredientRepository.deleteByName("Active-Caffeine");
    }

    private void p11() {
        BigDecimal percent = new BigDecimal(1.1);
        this.ingredientRepository.increaseIngredientPrice(percent);
    }

    private void p12() {
        BigDecimal percent = new BigDecimal(1.1);
        List<String> names = new ArrayList<>();
        names.add("Apple");
        names.add("Nettle");
        this.ingredientRepository.increaseIngredientPriceByName(names,percent);
    }

}
