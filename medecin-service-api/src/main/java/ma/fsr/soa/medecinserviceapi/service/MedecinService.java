package ma.fsr.soa.medecinserviceapi.service;

import ma.fsr.soa.cabinetrepo.model.Medecin;
import ma.fsr.soa.cabinetrepo.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinService {

    @Autowired
    MedecinRepository medecinRepository;

    // 1. CREATE
    public Medecin create(Medecin medecin) throws Exception {
        // Rule: Name is mandatory
        if (medecin.getNom() == null)
            throw new Exception("Le nom du médecin est obligatoire.");

        // Rule: Email is mandatory
        if (medecin.getEmail() == null)
            throw new Exception("L'email du médecin est obligatoire.");

        // Rule: Email must be valid (contains '@')
        if (!medecin.getEmail().contains("@"))
            throw new Exception("Email du médecin invalide.");

        // Rule: Specialty is mandatory
        if (medecin.getSpecialite() == null || medecin.getSpecialite().isEmpty())
            throw new Exception("La spécialité du médecin est obligatoire.");

        return medecinRepository.save(medecin);
    }

    // 2. UPDATE
//    public Medecin update(Long id, Medecin newMedecin) throws Exception {
//        Medecin existingMedecin = medecinRepository.findById(id).orElse(null);
//
//        if (existingMedecin == null) {
//            throw new Exception("Médecin introuvable: id = " + id);
//        }
//
//        // Apply updates with validation
//        if (newMedecin.getNom() != null)
//            existingMedecin.setNom(newMedecin.getNom());
//
//        if (newMedecin.getEmail() != null) {
//            if (!newMedecin.getEmail().contains("@"))
//                throw new Exception("Email du médecin invalide.");
//            existingMedecin.setEmail(newMedecin.getEmail());
//        }
//
//        if (newMedecin.getSpecialite() != null)
//            existingMedecin.setSpecialite(newMedecin.getSpecialite());
//
//        return medecinRepository.save(existingMedecin);
//    }

    // 3. FIND BY ID
    public Medecin findMedecin(Long id) throws Exception {
        Medecin medecin = medecinRepository.findById(id).orElse(null);
        if (medecin == null)
            throw new Exception("Médecin introuvable: id = " + id);
        return medecin;
    }

    // 4. DELETE
    public String delete(Long id) throws Exception {
        if (!medecinRepository.existsById(id)) {
            throw new Exception("Médecin introuvable: id = " + id);
        }
        medecinRepository.deleteById(id);
        return "Médecin supprimé avec succès";
    }

    // 5. LIST ALL
    public List<Medecin> listerMedecins() {
        return medecinRepository.findAll();
    }
}