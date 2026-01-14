package ma.fsr.soa.rendezvousserviceapi.web.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class RendezVousDto {
    private LocalDate dateRdv;
    private Long patientId;
    private Long medecinId;
}
