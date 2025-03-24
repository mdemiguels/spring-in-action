package tacos.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.model.Taco;

import java.util.List;

public interface TacoRepository extends CrudRepository<Taco, Long> {

    Page<Taco> findAll(Pageable page);
}
