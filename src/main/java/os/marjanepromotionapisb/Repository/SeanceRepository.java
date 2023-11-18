package os.marjanepromotionapisb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import os.marjanepromotionapisb.Entity.Salle;
import os.marjanepromotionapisb.Entity.Seance;
@RepositoryRestResource
@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
