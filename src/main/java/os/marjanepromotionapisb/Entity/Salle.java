package os.marjanepromotionapisb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Salle implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int nombrePlace;
    @ManyToOne @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToMany(mappedBy = "salle")
    private Collection<Place> places;

}
