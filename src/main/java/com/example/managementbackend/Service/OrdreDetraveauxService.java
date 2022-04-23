package com.example.managementbackend.Service;

import com.example.managementbackend.Repository.AttachementRepository;
import com.example.managementbackend.Repository.BondeCommandeRepository;
import com.example.managementbackend.Repository.OrdreDeTraveauxRepository;
import com.example.managementbackend.exception.ResourceNotFoundException;
import com.example.managementbackend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdreDetraveauxService {
    @Autowired
    private OrdreDeTraveauxRepository ordreRepo;

    @Autowired
    private BondeCommandeRepository bondeCommandeRepo;
//bochra
    public List<OrdreDeTraveaux> getAll() {
        return ordreRepo.findAll();
    }


    public OrdreDeTraveaux create(Long bondeCommandeId, OrdreDeTraveaux ordreDeTraveaux) {
        return bondeCommandeRepo.findById(bondeCommandeId).map(bondeCommande -> {
            ordreDeTraveaux.setBonDeCommande(bondeCommande);
            return ordreRepo.save(ordreDeTraveaux);
        }).orElseThrow(() -> new ResourceNotFoundException("bondeCommandeId " + bondeCommandeId+ " not found"));
    }

    public float geMontantTotalOT(long bcId) {
        return ordreRepo.getMontantTotalOT(bcId);
    }


    public Optional<OrdreDeTraveaux> getOTByCodeandBCId(String codeOrdre, long bcID) {
        return ordreRepo.findByCodeOrdreAndBonDeCommandeId(codeOrdre,bcID).map(ordreDeTraveaux -> ordreRepo.findByCodeOrdreAndBonDeCommandeId(codeOrdre,bcID)).orElseThrow(() -> new ResourceNotFoundException("codeOrdre " + codeOrdre+ " not found or bcId "+bcID+" not found"));
    }

    public List<OrdreDeTraveaux> getAllBYBC(Long bcID) {
        return ordreRepo.findByBonDeCommandeId(bcID);
    }
}
