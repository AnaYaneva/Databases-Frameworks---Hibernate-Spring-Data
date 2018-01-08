package bookshop.services.api;

import bookshop.entities.Book;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface BookService {

    void createAll(List<Book> books);

    void getAllBooksByAgeRestriction(String input);

    //p02
    void getAllByEditionTypeAndCopiesLessThan(String input, Long copies);

    //p03
    void getAllByPriceLowerThanAndPriceGreaterThan(double low, double high);

    //p04
    void getAllByReleaseDateNot(String year);

    //p05
    void getAllByReleaseDateBefore(String input) throws ParseException;

    //p07
    void getAllByTitleContains(String substr);


//    List<Book> getAllBooksAfter2000Year();
}
