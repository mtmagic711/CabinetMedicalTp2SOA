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

    public Consultation create(Consultation consultation) throws Exception {

        if (consultation.getRendezVous() == null || consultation.getRendezVous().getId() == null) {
            throw new Exception("Rendez-vous introuvable (ID manquant).");
        }

        RendezVous rdv = rendezVousRepository.findById(consultation.getRendezVous().getId())
                .orElseThrow(() -> new Exception("Rendez-vous introuvable."));

        if (consultation.getDateConsultation() == null) {
            throw new Exception("La date de consultation est obligatoire.");
        }

        if (consultation.getDateConsultation().isBefore(rdv.getDateRdv())) {
            throw new Exception("Date de consultation invalide.");
        }

        if (consultation.getRapport() == null || consultation.getRapport().length() < 10) {
            throw new Exception("Rapport de consultation insuffisant (min 10 caractères).");
        }

        consultation.setRendezVous(rdv);

        return consultationRepository.save(consultation);
    }

    public Consultation findConsultation(Long id) throws Exception {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new Exception("Consultation introuvable: id = " + id));
    }

    public List<Consultation> findByRendezVous(Long idRdv) {
        return consultationRepository.findByRendezVousId(idRdv);
    }


    public String delete(Long id) throws Exception {
        if (!consultationRepository.existsById(id))
            throw new Exception("Consultation introuvable");

        consultationRepository.deleteById(id);
        return "Consultation supprimée";
    }

    public List<Consultation> listerConsultations() {
        return consultationRepository.findAll();
    }
}