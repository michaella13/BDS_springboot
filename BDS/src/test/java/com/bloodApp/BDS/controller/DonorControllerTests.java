package com.bloodApp.BDS.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bloodApp.BDS.model.Donor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.bloodApp.BDS.services.DonorService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//END to END
@WebMvcTest(DonorService.class)
public class DonorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public DonorService donorService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenDonorObject_whenCreateDonor_thenReturnSavedDonor() throws Exception{

        // given - precondition or setup
        Donor donor = Donor.builder()
                .id(1)
                .firstName("INEZA")
                .lastName("Michaella")
                .email("ramesh@gmail.com")
                .blood_type("A+")
                .num_of_packets(2)
                .build();
        given(donorService.saveDonor(any(Donor.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));
        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/donors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(donor)));


        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(donor.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(donor.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(donor.getEmail())))
                .andExpect(jsonPath("$.blood_type",
                        is(donor.getBlood_type())))
                .andExpect(jsonPath("$.num_of_packets",
                        is(donor.getNum_of_packets())));

    }

    // JUnit test for Get All Donors REST API
    @Test
    public void givenListOfDonors_whenGetAllDonors_thenReturnDonorsList() throws Exception{
        // given - precondition or setup
        List<Donor> listOfDonors = new ArrayList<>();
        listOfDonors.add(Donor.builder().firstName("Ramesh").lastName("Fadatare").email("ramesh@gmail.com").build());
        listOfDonors.add(Donor.builder().firstName("Tony").lastName("Stark").email("tony@gmail.com").build());
        given(donorService.getAllDonors()).willReturn(listOfDonors);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/donors"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfDonors.size())));

    }

    // positive scenario - valid Donor id
    // JUnit test for GET Donor by id REST API
    @Test
    public void givenDonorId_whenGetDonorById_thenReturnDonorObject() throws Exception{
        // given - precondition or setup
        long DonorId = 1L;
        Donor donor = Donor.builder()
                .firstName("INEZA")
                .lastName("Michaella")
                .email("ramesh@gmail.com")
                .blood_type("A+")
                .num_of_packets(2)
                .build();
        given(donorService.getDonorById(DonorId)).willReturn(Optional.of(donor));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/donors/{id}", DonorId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(donor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(donor.getLastName())))
                .andExpect(jsonPath("$.email", is(donor.getEmail())));

    }

    // negative scenario - valid Donor id
    // JUnit test for GET Donor by id REST API
    @Test
    public void givenInvalidDonorId_whenGetDonorById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        long donorId = 1L;
        Donor donor = Donor.builder()
                .firstName("INEZA")
                .lastName("Michaella")
                .email("ramesh@gmail.com")
                .blood_type("A+")
                .num_of_packets(2)
                .build();
        given(donorService.getDonorById(donorId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/donors/{id}", donorId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update Donor REST API - positive scenario
    @Test
    public void givenUpdatedDonor_whenUpdateDonor_thenReturnUpdateDonorObject() throws Exception{
        // given - precondition or setup
        long DonorId = 1L;
        Donor savedDonor = Donor.builder()
                .firstName("INEZA")
                .lastName("Michaella")
                .email("ramesh@gmail.com")
                .blood_type("A+")
                .num_of_packets(2)
                .build();

        Donor updatedDonor = Donor.builder()
                .firstName("GIKUNDIRO")
                .lastName("Larissa")
                .email("ram@gmail.com")
                .blood_type("A-")
                .num_of_packets(1)
                .build();
        given(donorService.getDonorById(DonorId)).willReturn(Optional.of(savedDonor));
        given(donorService.updateDonor(any(Donor.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/donors/{id}", DonorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDonor)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedDonor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedDonor.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedDonor.getEmail())))
                 .andExpect(jsonPath("$.blood_type", is(updatedDonor.getBlood_type())))
                 .andExpect(jsonPath("$.num_of_packets", is(updatedDonor.getNum_of_packets())));
    }

    // JUnit test for update Donor REST API - negative scenario
    @Test
    public void givenUpdatedDonor_whenUpdateDonor_thenReturn404() throws Exception{
        // given - precondition or setup
        long DonorId = 1L;
        Donor savedDonor = Donor.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        Donor updatedDonor = Donor.builder()
                .firstName("Ram")
                .lastName("Jadhav")
                .email("ram@gmail.com")
                .build();
        given(donorService.getDonorById(DonorId)).willReturn(Optional.empty());
        given(donorService.updateDonor(any(Donor.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/donors/{id}", DonorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDonor)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete Donor REST API
    @Test
    public void givenDonorId_whenDeleteDonor_thenReturn200() throws Exception{
        // given - precondition or setup
        long DonorId = 1L;
        willDoNothing().given(donorService).deleteDonor(DonorId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/donors/{id}", DonorId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}


