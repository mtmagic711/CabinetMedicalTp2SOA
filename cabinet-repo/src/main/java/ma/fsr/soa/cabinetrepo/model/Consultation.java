package ma.fsr.soa.cabinetrepo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Consultation {
    @Id
    @GeneratedValue
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateConsultation;
    private String rapport;
    @OneToOne
    private RendezVous rendezVous;
}