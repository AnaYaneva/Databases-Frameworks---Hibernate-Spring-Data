package app.retake.repositories;

import app.retake.domain.models.AnimalAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnimalAidRepository extends JpaRepository<AnimalAid, Integer>{
    AnimalAid findByName(String name);
    void deleteAll();
}
