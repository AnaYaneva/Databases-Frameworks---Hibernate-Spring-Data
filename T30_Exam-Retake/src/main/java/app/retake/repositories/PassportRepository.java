package app.retake.repositories;


import app.retake.domain.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Component
public interface PassportRepository extends JpaRepository<Passport, String> {
    void deleteAll();

    List<Passport> findAllByOwnerPhoneNumber(String phoneNumber);
}
