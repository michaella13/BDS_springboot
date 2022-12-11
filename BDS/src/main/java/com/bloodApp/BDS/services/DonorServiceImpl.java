package com.bloodApp.BDS.services;

import com.bloodApp.BDS.model.Donor;
import com.bloodApp.BDS.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService{
    private DonorRepository donorRepository;

    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @Override
    public Optional<Donor> getDonorById(long id) {
        return donorRepository.findById(id);
    }

    @Override
    public Donor updateDonor(Donor updatedDonor) {
        return donorRepository.save(updatedDonor);
    }

    @Override
    public void deleteDonor(long id) {
        donorRepository.deleteById(id);
    }
}

