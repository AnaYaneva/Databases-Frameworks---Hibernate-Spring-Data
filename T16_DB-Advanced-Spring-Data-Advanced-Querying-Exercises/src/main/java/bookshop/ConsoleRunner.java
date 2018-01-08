package bookshop;

import bookshop.entities.Author;
import bookshop.entities.Book;
import bookshop.entities.Category;
import bookshop.repositories.AuthorRepository;
import bookshop.repositories.BookRepository;
import bookshop.repositories.CategoryRepository;
import bookshop.services.api.AuthorService;
import bookshop.services.api.BookService;
import bookshop.services.api.CategoryService;
import bookshop.utilities.ReadAuthorFile;
import bookshop.utilities.ReadBookFile;
import bookshop.utilities.ReadCategoryFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Component
@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private static final String AUTHORS_PATH = "D:\\SoftUni\\Java-DB\\Databases Frameworks - Hibernate & Spring Data\\Databases-Frameworks---Hibernate-Spring-Data\\T16_DB-Advanced-Spring-Data-Advanced-Querying-Exercises\\src\\main\\resources\\files\\14_authors.txt";
    private static final String BOOKS_PATH = "D:\\SoftUni\\Java-DB\\Databases Frameworks - Hibernate & Spring Data\\Databases-Frameworks---Hibernate-Spring-Data\\T16_DB-Advanced-Spring-Data-Advanced-Querying-Exercises\\src\\main\\resources\\files\\14_books.txt";
    private static final String CATEGORIES_PATH = "D:\\SoftUni\\Java-DB\\Databases Frameworks - Hibernate & Spring Data\\Databases-Frameworks---Hibernate-Spring-Data\\T16_DB-Advanced-Spring-Data-Advanced-Querying-Exercises\\src\\main\\resources\\files\\14_categories.txt";

    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;

    private ReadBookFile readBookFile;
    private ReadAuthorFile readAuthorFile;
    private ReadCategoryFile readCategoryFile;

    //private AuthorRepository authorRepository;
    //private CategoryRepository categoryRepository;
    //private BookRepository bookRepository;

    @Autowired
    public ConsoleRunner(BookService bookService, CategoryService categoryService, AuthorService authorService, ReadBookFile readBookFile, ReadAuthorFile readAuthorFile, ReadCategoryFile readCategoryFile) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.readBookFile = readBookFile;
        this.readAuthorFile = readAuthorFile;
        this.readCategoryFile = readCategoryFile;
    }

    @Override
    public void run(String... strings) throws Exception {
        solveProblem(7);
    }

    private void solveProblem(int i) throws IOException, ParseException {
        switch (i){
            case 0:
                p00();
                break;
            case 1:
                bookService.getAllBooksByAgeRestriction("teEN");
                break;
            case 2:
                bookService.getAllByEditionTypeAndCopiesLessThan("gold", 5000L);
                break;
            case 3:
                bookService.getAllByPriceLowerThanAndPriceGreaterThan(5,40);
                break;
            case 4:
                bookService.getAllByReleaseDateNot("2000");
                break;
            case 5:
                bookService.getAllByReleaseDateBefore("12-04-1992");
                break;
            case 6:
                authorService.getAllByFirstNameEndWith("dy");
                break;
            case 7:
                bookService.getAllByTitleContains("sK");
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
            case 13:
                p11();
                break;
            case 14:
                p12();
                break;
        }
    }

    private void p00() throws java.io.IOException {
        List<Author> authors = this.readAuthorFile.read(AUTHORS_PATH);
        List<Category> categories = this.readCategoryFile.read(CATEGORIES_PATH);
        this.authorService.createAll(authors);
        this.categoryService.createAll(categories);

        List<Book> books = this.readBookFile.read(BOOKS_PATH);
        this.bookService.createAll(books);
    }

    //private void p01() {
//
    //}
//
    //private void p02() {
//
    //}

    private void p03() {

    }

    private void p04() {

    }

    private void p05() {

    }

    private void p06() {

    }

    private void p07() {

    }

    private void p08() {

    }

    private void p09() {

    }

    private void p10() {

    }

    private void p11() {

    }

    private void p12() {

    }

    private void p13() {

    }

    private void p14() {

    }

    private void printTitlesOfBooks(List<Book> books) {
        books.stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }


}
