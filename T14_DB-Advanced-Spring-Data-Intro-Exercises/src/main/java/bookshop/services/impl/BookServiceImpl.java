package bookshop.services.impl;

import bookshop.entities.Book;
import bookshop.repositories.AuthorRepository;
import bookshop.repositories.BookRepository;
import bookshop.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
