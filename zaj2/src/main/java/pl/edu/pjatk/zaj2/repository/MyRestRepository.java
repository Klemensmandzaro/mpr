package pl.edu.pjatk.zaj2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.zaj2.service.Zwierze;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyRestRepository extends CrudRepository<Zwierze, Long> {
    List<Zwierze> findByName(String name);
    List<Zwierze> findByColor(String color);
    Optional<Zwierze> findById(Long id);
    List<Zwierze> findAll();
}
