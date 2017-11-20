package bookshop;

import bookshop.entities.Author;
import bookshop.entities.Book;
import bookshop.entities.Category;
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

import java.util.List;

@Component
@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private static final String AUTHORS_PATH = "D:\\SoftUni\\Java-DB\\Databases Frameworks - Hibernate & Spring Data\\Databases-Frameworks---Hibernate-Spring-Data\\T14_DB-Advanced-Spring-Data-Intro-Exercises\\src\\main\\resources\\files\\14_authors.txt";
    private static final String CATEGORIES_PATH = "D:\\SoftUni\\Java-DB\\Databases Frameworks - Hibernate & Spring Data\\Databases-Frameworks---Hibernate-Spring-Data\\T14_DB-Advanced-Spring-Data-Intro-Exercises\\src\\main\\resources\\files\\14_categories.txt";
    private static final String BOOKS_PATH = "D:\\SoftUni\\Java-DB\\Databases Frameworks - Hibernate & Spring Data\\Databases-Frameworks---Hibernate-Spring-Data\\T14_DB-Advanced-Spring-Data-Intro-Exercises\\src\\main\\resources\\files\\14_books.txt";

    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;

    private ReadBookFile readBookFile;
    private ReadAuthorFile readAuthorFile;
    private ReadCategoryFile readCategoryFile;

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
        //List<Author> authors = this.readAuthorFile.read(AUTHORS_PATH);
       // List<Category> categories = this.readCategoryFile.read(CATEGORIES_PATH);
        List<Book> books = this.readBookFile.read(BOOKS_PATH);

        //this.authorService.createAll(authors);
        //this.categoryService.createAll(categories);
       this.bookService.createAll(books);

//        printTitlesOfBooks(this.bookService.getAllBooksAfter2000Year());
    }

    private void printTitlesOfBooks(List<Book> books) {
        books.stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }


}
