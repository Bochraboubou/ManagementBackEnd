package com.example.managementbackend.Service;

import com.example.managementbackend.Repository.MarcheeRepository;
import com.example.managementbackend.Repository.MetierRepository;
import com.example.managementbackend.Repository.OrganisationRepository;
import com.example.managementbackend.exception.ResourceNotFoundException;
import com.example.managementbackend.model.Marchee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MarcheeService {
    @Autowired
    private MarcheeRepository marcheeRepo;

    @Autowired
    private OrganisationRepository organRepo;

    @Autowired
    private MetierRepository metierRepo;

    public List<Marchee> getAllMarcheesByOrganId(Long organId) {

        return marcheeRepo.findByOrgId(organId);
    }

    public List<Marchee> getAllMarcheestypeProjetByOrganId(Long organId) {

        return marcheeRepo.getAllMarcheeProjetbyOrg(organId);
    }

    public List<Marchee> getAllMarcheestypeMCByOrganId(Long organId) {

        return marcheeRepo.getAllMarcheeMCbyOrg(organId);
    }

    public Marchee create(Long organId,long metierId,Marchee marchee) {
        return organRepo.findById(organId).map(organisation -> {
            return metierRepo.findById(metierId).map(metier -> {
                marchee.setMetier(metier);
                marchee.setOrg(organisation);
                return marcheeRepo.save(marchee);
            }).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
        }).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
    }


    public ResponseEntity<?> delete(Long organId,Long marcheeId) {
        return marcheeRepo.findByIdAndOrgId(marcheeId, organId).map(marchee -> {
            marcheeRepo.delete(marchee);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("marchee not found with id " + marcheeId+ " and organisationId " + organId));
    }

    public Optional<Marchee> getMarcheebyCode(String codeMarchee) {
        return marcheeRepo.findByCode(codeMarchee).map(marchee -> marcheeRepo.findByCode(codeMarchee)).orElseThrow(() -> new ResourceNotFoundException("codeMarchee " + codeMarchee+ " not found"));
    }

    public Optional<Marchee> getMarcheebyId(long idMarchee) {
        return marcheeRepo.findById(idMarchee).map(marchee -> marcheeRepo.findById(idMarchee)).orElseThrow(() -> new ResourceNotFoundException("idMarchee " + idMarchee+ " not found"));
    }

    public Optional<Marchee> getMarcheebyCodeandOrganisation(String codeMarchee,long organId) {
        return marcheeRepo.findByCodeAndOrgId(codeMarchee,organId).map(marchee -> marcheeRepo.findByCodeAndOrgId(codeMarchee,organId)).orElseThrow(() -> new ResourceNotFoundException("codeMarchee " + codeMarchee+ " not found or idOrgan "+organId+" not found"));
    }


    public Optional<Marchee[]> getMarcheebyMetierandOrganisation(long metierId,long organId) {
        return marcheeRepo.findByMetierIdAndOrgId(metierId,organId).map(marchees -> marcheeRepo.findByMetierIdAndOrgId(metierId,organId)).orElseThrow(() -> new ResourceNotFoundException("metierId " + metierId+ " not found or idOrgan "+organId+" not found"));
    }


}
