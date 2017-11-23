package app.repositories;

import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<BasicLabel, Long> {
}
