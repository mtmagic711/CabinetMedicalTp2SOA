package ma.fsr.soa.rendezvousserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.cabinetrepo.repository.MedecinRepository;
import ma.fsr.soa.cabinetrepo.repository.PatientRepository;
import ma.fsr.soa.cabinetrepo.repository.RendezVousRepository;
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

    public RendezVous create(RendezVous rd) throws Exception {
        if (rd.getDateRdv().isBefore(LocalDate.now()))
            throw new Exception("La date du rendez-vous doit être future.");

        if (rd.getPatient() == null || rd.getPatient().getId() == null ||
                !patientRepository.existsById(rd.getPatient().getId())) {
            throw new Exception("Patient introuvable.");
        }

        if (rd.getMedecin() == null || rd.getMedecin().getId() == null ||
                !medecinRepository.existsById(rd.getMedecin().getId())) {
            throw new Exception("Médecin introuvable");
        }

        if (rd.getStatut() == null) {
            rd.setStatut("PLANIFIE");
        } else {
            validateStatus(rd.getStatut());
        }

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