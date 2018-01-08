package bookshop.services.impl;

import bookshop.entities.Author;
import bookshop.repositories.AuthorRepository;
import bookshop.services.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(long id) {
        return this.authorRepository.findOne(id);
    }

    @Override
    public void createAll(List<Author> authors) {
        this.authorRepository.save(authors);
    }

    //p06
    @Override
    public void getAllByFirstNameEndWith(String postfix){
        List<Author> found = this.authorRepository.findAllByFirstNameEndingWith(postfix);

        printNames(found);
    }

    private void printNames(List<Author> found) {
        for (Author author : found) {
            System.out.println(author.getFirstName()+" "+author.getLastName());
        }
    }
}
