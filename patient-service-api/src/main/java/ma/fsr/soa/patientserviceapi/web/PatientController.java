package ma.fsr.soa.patientserviceapi.web;

import jakarta.persistence.PostUpdate;
import ma.fsr.soa.cabinetrepo.model.Patient;
import ma.fsr.soa.patientserviceapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/v1/patients")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping
    public Patient create(@RequestBody Patient patient) throws Exception {
        return patientService.create(patient);
    }

    @GetMapping("/{id}")
    public Patient findPatient(@PathVariable Long id) throws Exception {
        return patientService.findPatient(id);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return patientService.delete(id);
    }
    @GetMapping
    public List<Patient> listerPatients() {
        return patientService.listerPatients();
    }

}
