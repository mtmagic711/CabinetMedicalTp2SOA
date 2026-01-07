package ma.fsr.soa.consultationserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.Consultation;
import ma.fsr.soa.cabinetrepo.model.RendezVous;
import ma.fsr.soa.cabinetrepo.repository.ConsultationRepository;
import ma.fsr.soa.cabinetrepo.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    RendezVousRepository rendezVousRepository;

    // 1. CREATE
    public Consultation create(Consultation consultation) throws Exception {

        // Rule: Rendez-vous must exist
        if (consultation.getRendezVous() == null || consultation.getRendezVous().getId() == null) {
            throw new Exception("Rendez-vous introuvable (ID manquant).");
        }

        // Fetch the actual RendezVous object from DB to check dates
        RendezVous rdv = rendezVousRepository.findById(consultation.getRendezVous().getId())
                .orElseThrow(() -> new Exception("Rendez-vous introuvable."));

        // Rule: Consultation Date is mandatory
        if (consultation.getDateConsultation() == null) {
            throw new Exception("La date de consultation est obligatoire.");
        }

        // Rule: Consultation Date >= RendezVous Date
        if (consultation.getDateConsultation().isBefore(rdv.getDateRdv())) {
            throw new Exception("Date de consultation invalide.");
        }

        // Rule: Report is mandatory and >= 10 chars
        if (consultation.getRapport() == null || consultation.getRapport().length() < 10) {
            throw new Exception("Rapport de consultation insuffisant (min 10 caractères).");
        }

        // Link the full object before saving
        consultation.setRendezVous(rdv);

        return consultationRepository.save(consultation);
    }

    // 2. FIND BY ID
    public Consultation findConsultation(Long id) throws Exception {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new Exception("Consultation introuvable: id = " + id));
    }

    // 3. FIND BY RENDEZ-VOUS (Required for ESB Mapping)
    public List<Consultation> findByRendezVous(Long idRdv) {
        return consultationRepository.findByRendezVousId(idRdv);
    }

    // 4. DELETE
    public String delete(Long id) throws Exception {
        if (!consultationRepository.existsById(id))
            throw new Exception("Consultation introuvable");

        consultationRepository.deleteById(id);
        return "Consultation supprimée";
    }

    // 5. LIST ALL
    public List<Consultation> listerConsultations() {
        return consultationRepository.findAll();
    }
}