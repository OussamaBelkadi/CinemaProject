package os.marjanepromotionapisb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import os.marjanepromotionapisb.Entity.Salle;
import os.marjanepromotionapisb.Entity.Ticket;
@RepositoryRestResource

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
