package com.bloodApp.BDS.model;

import com.bloodApp.BDS.entity.Donor;
import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.assertEquals;
//UNIT TESTS
public class DonorModelTests {
    Donor donor = new Donor(1, "Clarisee", "Iradukunda", "cla@gmail.com","B-",2 );
    @Test
    public void getDonorFirstNameTest(){
        assertEquals("Clarisee", donor.getFirstName());
    }
    @Test
    public void getDonorEmailTest(){
        String email = "cla@gmail.com";
        Donor donor = new Donor(1, "Clarisee", "Iradukunda", "cla@gmail.com","B-",2 );
        assertEquals(email, donor.getEmail());
    }
}
