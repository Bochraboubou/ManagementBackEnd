package com.example.managementbackend.Repository;

import com.example.managementbackend.model.BondeCommande;
import com.example.managementbackend.model.Entreprise;
import com.example.managementbackend.model.Marchee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BondeCommandeRepository extends JpaRepository<BondeCommande, Long> {

    List<BondeCommande> findByMarcheeId(long marcheeId);
    Optional<BondeCommande> findByIdAndMarcheeId(long id, long marcheeId);

}
