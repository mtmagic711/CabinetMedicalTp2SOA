package ma.fsr.soa.cabinetrepo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private String genre;
    private String telephone;
}
