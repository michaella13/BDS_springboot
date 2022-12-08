package com.bloodApp.BDS.repository;

import com.bloodApp.BDS.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
