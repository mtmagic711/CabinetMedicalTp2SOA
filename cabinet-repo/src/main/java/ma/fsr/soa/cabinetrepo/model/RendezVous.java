package ma.fsr.soa.cabinetrepo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RendezVous {

    @Id
    @GeneratedValue
    Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRdv;
    private String statut;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecin medecin;
}
