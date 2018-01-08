package bookshop.repositories;

import bookshop.entities.Book;
import bookshop.enums.AgeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {

    //p01
    List<Book> findAllByAgeRestriction(int ageRestriction);

    //p02
    List<Book> findAllByEditionTypeAndCopiesLessThan(int editionType, Long copies);

    //p03
    List<Book> findAllByPriceLessThan(double low);
    List<Book> findAllByPriceGreaterThan(double high);

    //p04
    @Query("select b from Book b where substring(b.releaseDate,1,4) not like :year")
    List<Book> findAllByReleaseDateNot(@Param(value = "year") String year);

    //p05
    List<Book> findAllByReleaseDateBefore(Date date);

    //p07
    List<Book> findAllByTitleContains(String substr);
}