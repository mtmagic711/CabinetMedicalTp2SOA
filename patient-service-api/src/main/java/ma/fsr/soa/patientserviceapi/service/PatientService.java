package ma.fsr.soa.patientserviceapi.service;
import ma.fsr.soa.cabinetrepo.model.Patient;
import ma.fsr.soa.cabinetrepo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public Patient create(Patient patient) throws Exception{
        if (patient.getNom() == null)
            throw new Exception("Le nom du patient est obligatoire.");
        if (patient.getTelephone() == null)
            throw new Exception("Le téléphone du patient est obligatoire.");
        if (patient.getDateNaissance().isAfter(LocalDate.now()))
            throw new Exception("La date de naissance ne peut pas être future.");
        return patientRepository.save(patient);
    }

//    public Patient update(Long id, Patient newPatient) throws Exception {
//        // First, check if the patient exists
//        Patient existingPatient = patientRepository.findById(id).orElse(null);
//
//        if (existingPatient == null) {
//            throw new Exception("Patient introuvable: id = " + id);
//        } else {
//            // Update the fields if they are provided
//            if (newPatient.getNom() != null)
//                existingPatient.setNom(newPatient.getNom());
//
//            if (newPatient.getTelephone() != null)
//                existingPatient.setTelephone(newPatient.getTelephone());
//
//            if (newPatient.getDateNaissance() != null) {
//                // Re-validate date rule for update
//                if (newPatient.getDateNaissance().isAfter(LocalDate.now()))
//                    throw new Exception("La date de naissance ne peut pas être future");
//                existingPatient.setDateNaissance(newPatient.getDateNaissance());
//            }
//
//            return patientRepository.save(existingPatient);
//        }
//    }

    public Patient findPatient(Long id) throws Exception {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            throw new Exception("Patient introuvable: id = " + id);
        return patient;
    }

    public String delete(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            return "Patient introuvable";
        else {
            patientRepository.deleteById(id);
            return "Patient a ete supprime";
        }
    }

    public List<Patient> listerPatients() {
        return patientRepository.findAll();
    }
}
