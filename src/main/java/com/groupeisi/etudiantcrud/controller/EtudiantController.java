package com.groupeisi.etudiantcrud.controller;

import com.groupeisi.etudiantcrud.exception.ResourceNotFoundException;
import com.groupeisi.etudiantcrud.model.Etudiant;
import com.groupeisi.etudiantcrud.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class EtudiantController {
        @Autowired
        private EtudiantRepository etudiantRepository;

        @GetMapping("/etudiants")
        public List<Etudiant> getAllEtudiants() {
            return etudiantRepository.findAll();
        }

        @GetMapping("/etudiants/{id}")
        public ResponseEntity<Etudiant> getEtudiantById(@PathVariable(value = "id") Long etudiantId)
                throws ResourceNotFoundException {
            Etudiant etudiant = etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new ResourceNotFoundException("Etudiant not found for this id :: " + etudiantId));
            return ResponseEntity.ok().body(etudiant);
        }

        @PostMapping("/etudiants")
        public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
            return etudiantRepository.save(etudiant);
        }

        @PutMapping("/etudiants/{id}")
        public ResponseEntity<Etudiant> updateEtudiant(@PathVariable(value = "id") Long etudiantId,
                                                       @RequestBody Etudiant etudiantDetails) throws ResourceNotFoundException {
            Etudiant etudiant = etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new ResourceNotFoundException("Etudiant not found for this id :: " + etudiantId));

            etudiant.setEmailId(etudiantDetails.getEmailId());
            etudiant.setLastName(etudiantDetails.getLastName());
            etudiant.setFirstName(etudiantDetails.getFirstName());
            final Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
            return ResponseEntity.ok(updatedEtudiant);
        }

        @DeleteMapping("/etudiants/{id}")
        public Map<String, Boolean> deleteEtudiant(@PathVariable(value = "id") Long etudiantId)
                throws ResourceNotFoundException {
            Etudiant etudiant = etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new ResourceNotFoundException("Etudiant not found for this id :: " + etudiantId));

            etudiantRepository.delete(etudiant);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }

    }
