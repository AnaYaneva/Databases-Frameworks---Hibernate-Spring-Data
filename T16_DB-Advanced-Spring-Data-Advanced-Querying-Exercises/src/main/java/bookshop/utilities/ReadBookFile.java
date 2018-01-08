package bookshop.utilities;

import bookshop.entities.Book;
import bookshop.entities.Category;
import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.services.api.AuthorService;
import bookshop.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ReadBookFile {

    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Autowired
    public ReadBookFile(CategoryService categoryService, AuthorService authorService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    public <A> List<Book> read(String filePath) throws IOException {
        List<Book> books=new ArrayList<>();
        Path bookPath= Paths.get(filePath);

        final SimpleDateFormat dtf=new SimpleDateFormat("d/M/yyyy");
        List<Category> allCategories=this.categoryService.findAll();
        Random random=new Random();

        for (String line : Files.readAllLines(bookPath).stream().skip(1).toArray(String[]::new)) {
            String[] tokens=line.split("\\s+");
            try{
                Constructor<Book> declaredConstructor=Book.class.getDeclaredConstructor();
                Book book=declaredConstructor.newInstance();
                book.setEditionType(EditionType.values()[Integer.parseInt(tokens[0])]);
                Date date=dtf.parse(tokens[1]);
                book.setReleaseDate(date);
                book.setCopies(Integer.parseInt(tokens[2]));
                book.setPrice(Double.parseDouble(tokens[3]));
                book.setAgeRestriction(AgeRestriction.values()[Integer.parseInt(tokens[4])]);

                if(tokens.length > 6) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 5; i < tokens.length; i++) {
                        sb.append(tokens[i]).append(" ");
                    }
                    book.setTitle(sb.toString().trim());
                } else {
                    book.setTitle(tokens[5]);
                }

                    Set<Category> categories=new HashSet<>();

                for (int i = 0; i < 3; i++) {
                    int index=random.nextInt(allCategories.size());
                    categories.add(allCategories.get(index));
                }

                book.setCategories(categories);
                book.setAuthor(this.authorService.findById(random.nextInt(30)));
                books.add(book);
            }catch (NoSuchMethodException |
                    InvocationTargetException |
                    InstantiationException |
                    IllegalAccessException |
                    ParseException e){
                e.printStackTrace();
            }
        }

        return books;
    }
}
