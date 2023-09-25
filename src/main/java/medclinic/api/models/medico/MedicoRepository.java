package medclinic.api.models.medico;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// 1- SPRING DATA FICARÁ AQUI
// 2- A interface herdará os métodos do JPA passando como parâmetro a Entidade e o tipo do atributo da primary key da Entidade
public interface MedicoRepository extends JpaRepository<Medico, Long>{
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
