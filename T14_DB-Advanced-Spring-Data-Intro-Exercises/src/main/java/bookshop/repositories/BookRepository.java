package bookshop.repositories;

import bookshop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

//        @Query(value = "SELECT * \n"+
//        "FROM books AS b \n"+
//        "WHERE year(b.release_date > 2000;", nativeQuery =true)
//        List<Book> getBooksByReleaseDateAfter2000();

}