package bookshop.services.impl;

import bookshop.entities.Book;
import bookshop.enums.AgeRestriction;
import bookshop.enums.EditionType;
import bookshop.repositories.BookRepository;
import bookshop.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createAll(List<Book> books) {
        this.bookRepository.save(books);
    }

//    @Override
//    public List<Book> getAllBooksAfter2000Year() {
//        return this.bookRepository.getBooksByReleaseDateAfter2000();
//    }

    //p01
    @Override
    public void getAllBooksByAgeRestriction(String input) {
        input = input.toUpperCase();
        AgeRestriction ageRestriction = (AgeRestriction.valueOf(input));
        List<Book> found = this.bookRepository.findAllByAgeRestriction(ageRestriction.getValue());

        printTitles(found);
    }


    //p02
    @Override
    public void getAllByEditionTypeAndCopiesLessThan(String input, Long copies) {
        input = input.toUpperCase();
        EditionType editionType = (EditionType.valueOf(input));
        List<Book> found = this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType.getValue(), copies);

        printTitles(found);
    }

    //p03
    @Override
    public void getAllByPriceLowerThanAndPriceGreaterThan(double low, double high){
        List<Book> found = new ArrayList<>();
        found.addAll(this.bookRepository.findAllByPriceLessThan(low));
        found.addAll(this.bookRepository.findAllByPriceGreaterThan(high));
        found.stream().sorted((b1,b2)->b1.getTitle().compareTo(b2.getTitle()));

        printTitlesAndPrice(found);
    }

    //p04
    @Override
    public void getAllByReleaseDateNot(String year){

        List<Book> found = this.bookRepository.findAllByReleaseDateNot(year);

        printTitles(found);
    }

    //p05
    @Override
    public void getAllByReleaseDateBefore(String input) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Date date=dateFormat.parse(input);

        List<Book> found = this.bookRepository.findAllByReleaseDateBefore(date);

        printTitlesEditionTypeAndPrice(found);
    }

    //p07
    @Override
    public void getAllByTitleContains(String substr){
        List<Book> found = this.bookRepository.findAllByTitleContains(substr);

        printTitles(found);
    }

    private void printTitles(List<Book> found) {
        for (Book book : found) {
            System.out.println(book.getTitle());
        }
    }

    private void printTitlesAndPrice(List<Book> found) {
        for (Book book : found) {
            System.out.printf("%s - %.2f\n",book.getTitle(),book.getPrice());
        }
    }

    private void printTitlesEditionTypeAndPrice(List<Book> found) {
        for (Book book : found) {
            System.out.printf("%s %s %.2f\n",book.getTitle(),
                    book.getEditionType(), book.getPrice());
        }
    }

}
