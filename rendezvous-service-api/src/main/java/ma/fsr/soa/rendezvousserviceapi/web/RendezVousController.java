package ma.fsr.soa.rendezvousserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.rendezvousserviceapi.service.RendezVousService;
import ma.fsr.soa.rendezvousserviceapi.web.dto.RendezVousDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/rendezvous")
public class RendezVousController {

    @Autowired
    RendezVousService rendezVousService;

    @GetMapping
    public List<RendezVous> getAll() {
        return rendezVousService.listerRendezVous();
    }

    @GetMapping("/{id}")
    public RendezVous findRendezVous(@PathVariable Long id) throws Exception {
        return rendezVousService.findRendezVous(id);
    }

    @GetMapping("/patient/{id}")
    public List<RendezVous> findByPatient(@PathVariable Long id) {
        return rendezVousService.findByPatient(id);
    }

    @GetMapping("/medecin/{id}")
    public List<RendezVous> findByMedecin(@PathVariable Long id) {
        return rendezVousService.findByMedecin(id);
    }

    @PostMapping
    public RendezVous create(@RequestBody RendezVousDto rdvDto) throws Exception {
        return rendezVousService.create(rdvDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        return rendezVousService.delete(id);
    }
}