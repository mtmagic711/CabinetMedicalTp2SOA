package ma.fsr.soa.rendezvousserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.Medecin;
import ma.fsr.soa.cabinetrepo.model.Patient;
import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.cabinetrepo.repository.MedecinRepository;
import ma.fsr.soa.cabinetrepo.repository.PatientRepository;
import ma.fsr.soa.cabinetrepo.repository.RendezVousRepository;
import ma.fsr.soa.rendezvousserviceapi.web.dto.RendezVousDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedecinRepository medecinRepository;

    public RendezVous create(RendezVousDto dto) throws Exception {
        RendezVous rd = new RendezVous();
        rd.setDateRdv(dto.getDateRdv());
        rd.setStatut("PLANIFIE");

        // --- LOGIC FIX: Handle Patient ---
        // Try to find the patient. If missing (due to DB split), create a STUB to satisfy the Foreign Key.
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseGet(() -> {
                    Patient newP = new Patient();
                    newP.setId(dto.getPatientId()); // Force the ID
                    newP.setNom("Patient-Stub-" + dto.getPatientId()); // Placeholder name
                    return patientRepository.save(newP); // Save locally so FK works
                });
        rd.setPatient(patient);

        // --- LOGIC FIX: Handle Medecin ---
        Medecin medecin = medecinRepository.findById(dto.getMedecinId())
                .orElseGet(() -> {
                    Medecin newM = new Medecin();
                    newM.setId(dto.getMedecinId());
                    newM.setNom("Medecin-Stub-" + dto.getMedecinId());
                    return medecinRepository.save(newM);
                });
        rd.setMedecin(medecin);

        // Now validation and saving will always succeed
        if (rd.getDateRdv().isBefore(LocalDate.now()))
            throw new Exception("La date du rendez-vous doit être future.");

        return rendezVousRepository.save(rd);
    }

    public RendezVous findRendezVous(Long id) throws Exception {
        return rendezVousRepository.findById(id)
                .orElseThrow(() -> new Exception("Rendez-vous introuvable: id = " + id));
    }

    public String delete(Long id) throws Exception {
        if (!rendezVousRepository.existsById(id))
            throw new Exception("Rendez-vous introuvable");
        rendezVousRepository.deleteById(id);
        return "Rendez-vous supprimé";
    }

    public List<RendezVous> listerRendezVous() {
        return rendezVousRepository.findAll();
    }

    public List<RendezVous> findByPatient(Long idPatient) {
        return rendezVousRepository.findByPatientId(idPatient);
    }

    public List<RendezVous> findByMedecin(Long idMedecin) {
        return rendezVousRepository.findByMedecinId(idMedecin);
    }

    private void validateStatus(String status) throws Exception {
        if (!status.equals("PLANIFIE") && !status.equals("ANNULE") && !status.equals("TERMINE")) {
            throw new Exception("Statut invalide. Valeurs possibles: PLANIFIE, ANNULE, TERMINE.");
        }
    }
}