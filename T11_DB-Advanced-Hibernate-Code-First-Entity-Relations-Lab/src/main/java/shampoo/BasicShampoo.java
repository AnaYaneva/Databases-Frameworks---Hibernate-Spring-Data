package shampoo;

import ingredient.basic.BasicIngredient;
import label.BasicLabel;
import batch.ProductionBatch;
import size.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="shampoo_type",discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="id")
    private long id;

    @Basic
    private String brand;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="label_id", referencedColumnName = "id")
    private BasicLabel label;

    //private ProductionBatch batch;

    @Basic
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="shampoos_ingredients",joinColumns = @JoinColumn(name="shampoo_id",referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="ingredient_id",referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    public BasicShampoo() {
    }

    public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
        this.brand = brand;
        this.label = label;
        this.price = price;
        this.size = size;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BasicLabel getLabel() {
        return this.label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

   //public ProductionBatch getBatch() {
   //    return this.batch;
   //}

   //public void setBatch(ProductionBatch batch) {
   //    this.batch = batch;
   //}

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
