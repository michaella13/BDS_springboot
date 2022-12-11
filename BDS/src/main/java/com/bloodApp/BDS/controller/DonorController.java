package com.bloodApp.BDS.controller;

import com.bloodApp.BDS.model.Donor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.bloodApp.BDS.services.DonorService;

import java.util.List;


@RestController
@RequestMapping("/donors")
public class DonorController {

    private DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Donor createDonor(@RequestBody Donor donor){
        return donorService.saveDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors(){
        return donorService.getAllDonors();
    }

    @GetMapping("{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable("id") long DonorId){
        return donorService.getDonorById(DonorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable("id") long DonorId,
                                                   @RequestBody Donor donor){
        return donorService.getDonorById(DonorId)
                .map(savedDonor -> {
                    savedDonor.setFirstName(donor.getFirstName());
                    savedDonor.setLastName(donor.getLastName());
                    savedDonor.setEmail(donor.getEmail());
                    savedDonor.setBlood_type(donor.getBlood_type());
                    savedDonor.setNum_of_packets(donor.getNum_of_packets());

                    Donor updatedDonor = donorService.updateDonor(savedDonor);
                    return new ResponseEntity<>(updatedDonor, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable("id") long DonorId){

        donorService.deleteDonor(DonorId);

        return new ResponseEntity<String>("Donor deleted successfully!.", HttpStatus.OK);

    }
}
