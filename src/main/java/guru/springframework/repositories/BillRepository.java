package guru.springframework.repositories;

import guru.springframework.domain.Bill;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {
    List<Bill> findAll();

    Optional<Bill> findById(Long id);
}
