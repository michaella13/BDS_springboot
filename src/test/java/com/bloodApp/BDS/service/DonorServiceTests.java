package com.bloodApp.BDS.service;

import com.bloodApp.BDS.entity.Donor;
import com.bloodApp.BDS.repository.DonorRepository;
import com.bloodApp.BDS.services.DonorService;
import com.bloodApp.BDS.services.DonorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class DonorServiceTests {
    @Mock
    private DonorRepository donorRepositoryMock;

    @InjectMocks
    private DonorServiceImpl serviceImpl;

    private Donor donor = new Donor(1, "Clarisee", "Iradukunda", "cla@gmail.com","B-",2 );
    @Test
    public void givenDonorObject_returnDonorObject() throws Exception {
        when(donorRepositoryMock.findById(donor.getId())).thenReturn(Optional.empty());
        Donor savedDonor = serviceImpl.saveDonor(donor);


        when(donorRepositoryMock.save(donor)).thenReturn(donor);
        System.out.println(donorRepositoryMock);
        System.out.println(serviceImpl);



        System.out.println(savedDonor);
        assertThat(savedDonor).isNotNull();
    }
}
