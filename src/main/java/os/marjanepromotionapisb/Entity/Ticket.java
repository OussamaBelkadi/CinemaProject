package os.marjanepromotionapisb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25 )
    private String nomClient;
    private double prix;
    @Column(unique = true, nullable = true)
    private Integer codePayement;
    private boolean reserver;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;
}
