package ma.fsr.soa.cabinetrepo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medecin {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String specialite;
    private String email;
}
