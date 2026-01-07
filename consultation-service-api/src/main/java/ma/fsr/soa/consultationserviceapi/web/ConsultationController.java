package ma.fsr.soa.consultationserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.Consultation;
import ma.fsr.soa.consultationserviceapi.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/consultations")
public class ConsultationController {

    @Autowired
    ConsultationService consultationService;

    @GetMapping
    public List<Consultation> getAll() {
        return consultationService.listerConsultations();
    }

    @GetMapping("/{id}")
    public Consultation findConsultation(@PathVariable Long id) throws Exception {
        return consultationService.findConsultation(id);
    }

    // Matches ESB route: /api/consultations/rendezvous/{id}
    @GetMapping("/rendezvous/{id}")
    public List<Consultation> findByRendezVous(@PathVariable Long id) {
        return consultationService.findByRendezVous(id);
    }

    @PostMapping
    public Consultation create(@RequestBody Consultation consultation) throws Exception {
        return consultationService.create(consultation);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        return consultationService.delete(id);
    }
}