package bookshop.services.api;

import bookshop.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    void createAll(List<Book> books);

//    List<Book> getAllBooksAfter2000Year();
}
