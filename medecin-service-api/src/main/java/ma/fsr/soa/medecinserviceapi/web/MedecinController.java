package ma.fsr.soa.medecinserviceapi.web;

import ma.fsr.soa.cabinetrepo.model.Medecin;
import ma.fsr.soa.medecinserviceapi.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/medecins")
public class MedecinController {

    @Autowired
    MedecinService medecinService;

    @GetMapping
    public List<Medecin> getAll() {
        return medecinService.listerMedecins();
    }

    @GetMapping("/{id}")
    public Medecin findMedecin(@PathVariable Long id) throws Exception{
        return medecinService.findMedecin(id);
    }

    @PostMapping
    public Medecin create(@RequestBody Medecin medecin) throws Exception{
        return medecinService.create(medecin);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Medecin medecin) {
//        try {
//            return ResponseEntity.ok(medecinService.update(id, medecin));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        return medecinService.delete(id);
    }
}