package com.bloodApp.BDS.repository;

import com.bloodApp.BDS.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    boolean existsById(Number id);
    Optional<Donor> findById(Number id);
}
