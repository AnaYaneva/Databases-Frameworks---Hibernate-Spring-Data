package bookshop.services.api;

import bookshop.entities.Author;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {

    Author findById(long id);

    void createAll(List<Author> authors);

    //p06
    void getAllByFirstNameEndWith(String postfix);
}
