package bookshop.services.api;

import bookshop.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    List<Category> findAll();

    void createAll(List<Category> categories);
}
