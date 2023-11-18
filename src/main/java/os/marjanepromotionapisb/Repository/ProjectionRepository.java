package os.marjanepromotionapisb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import os.marjanepromotionapisb.Entity.Projection;
import os.marjanepromotionapisb.Entity.Salle;

@RepositoryRestResource
@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
