package bookshop.entities;

import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(nullable = false)
    private int ageRestriction;

    @Column(nullable = false)
    private int copies;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private int editionType;

    @Column(nullable = false)
    private double price;

    private Date releaseDate;

    @Column(nullable = false, length = 50)
    private String title;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name="authorId", foreignKey=@ForeignKey(name="fk_books_authors"))
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="books_category",
            joinColumns = @JoinColumn(name="bookId"),
            inverseJoinColumns = @JoinColumn(name="categoryId"),
            foreignKey = @ForeignKey(name="fk_books_categories"),
            inverseForeignKey = @ForeignKey(name="fk_categories_books")
    )
    private Set<Category> categories;

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction.getValue();
    }

    public int getCopies() {
        return this.copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEditionType() {
        return this.editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType.getValue();
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
