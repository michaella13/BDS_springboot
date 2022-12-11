package com.bloodApp.BDS.repository;

import com.bloodApp.BDS.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    boolean existsById(Number id);
    Optional<Donor> findById(Number id);
}
