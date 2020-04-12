package ba.delalich.tablanette.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bull {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer bullId;

    @OneToMany(mappedBy = "bull")
    private Set<PlayerDealResult> dealResult = new HashSet<>();

    @OneToMany(mappedBy = "bull")
    private Set<PlayerTablanetteResult> tablanetteResult = new HashSet<>();
}
