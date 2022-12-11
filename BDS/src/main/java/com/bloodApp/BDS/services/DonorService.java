package com.bloodApp.BDS.services;

import com.bloodApp.BDS.model.Donor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface DonorService {
    Donor saveDonor(Donor donor);
    List<Donor> getAllDonors();
    Optional<Donor> getDonorById(long id);
    Donor updateDonor(Donor updatedDonor);
    void deleteDonor(long id);
}
