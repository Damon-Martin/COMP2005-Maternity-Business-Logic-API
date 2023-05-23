package uk.md.MaternityCalculationsAPI.IntegrationTests.FunctionalTests;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import uk.md.MaternityCalculationsAPI.Controllers.AdmissionsController;
import uk.md.MaternityCalculationsAPI.Models.MeanDuration;
import uk.md.MaternityCalculationsAPI.Models.PatientCustom;

import java.net.URI;
import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdmissionsController.class)
public class ExpectedResultsIntTest {
    @Autowired
    MockMvc mvc;

    // 2 Valid Days: All Equal Number so returning 2 days
    @Test
    void test_busiest_days_int_test() throws Exception {
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("THURSDAY");
        expectedResult.add("SATURDAY");

        // Act
        RequestBuilder req = MockMvcRequestBuilders.get("/api/BusiestDayOfWeek");

        MvcResult res = this.mvc.perform(req).andReturn();

        String rawJsonBody = res.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> actualResult = objectMapper.readValue(rawJsonBody, new TypeReference<List<String>>() {});

        // Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_patients_seen_int_test() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/PatientsSeen/%7Bid%7D?id=4");
        List<PatientCustom> expected = new ArrayList<PatientCustom>();

        PatientCustom patient1 = new PatientCustom();
        patient1.setPatientID(2);
        patient1.setForename("Heather");
        patient1.setSurname("Carter");
        patient1.setNhsNumber("2224446666");

        expected.add(patient1);

        MvcResult res = this.mvc.perform(req).andReturn();
        String rawJsonBody = res.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<PatientCustom> actualResult = objectMapper.readValue(rawJsonBody, new TypeReference<List<PatientCustom>>() {});

        // This is Required Doesn't like the diff memory locations
        Assertions.assertEquals(expected.get(0).getPatientID(), actualResult.get(0).getPatientID());
        Assertions.assertEquals(expected.get(0).getForename(), actualResult.get(0).getForename());
        Assertions.assertEquals(expected.get(0).getSurname(), actualResult.get(0).getSurname());
        Assertions.assertEquals(expected.get(0).getNhsNumber(), actualResult.get(0).getNhsNumber());
        Assertions.assertEquals(expected.size(), actualResult.size());
    }

    @Test
    void test_discharge_quick_func_int_test() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/api/DischargedQuick");
        List<PatientCustom> expected = new ArrayList<PatientCustom>();

        PatientCustom patient1 = new PatientCustom();
        patient1.setPatientID(2);
        patient1.setForename("Heather");
        patient1.setSurname("Carter");
        patient1.setNhsNumber("2224446666");
        expected.add(patient1);


        MvcResult res = this.mvc.perform(req).andReturn();
        String rawJsonBody = res.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<PatientCustom> actualResult = mapper.readValue(rawJsonBody, new TypeReference<List<PatientCustom>>() {});

        // This is Required Doesn't like the diff memory locations
        Assertions.assertEquals(expected.get(0).getPatientID(), actualResult.get(0).getPatientID());
        Assertions.assertEquals(expected.get(0).getForename(), actualResult.get(0).getForename());
        Assertions.assertEquals(expected.get(0).getSurname(), actualResult.get(0).getSurname());
        Assertions.assertEquals(expected.get(0).getNhsNumber(), actualResult.get(0).getNhsNumber());
        Assertions.assertEquals(expected.size(), actualResult.size());
    }

    @Test
    void test_avg_duration_int_test() throws Exception {
        // Arrange
        // Expected
        long expectedTime = ((24660 + 294360) / 2);

        URI fullUri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/AvgDurationByStaff/")
                .buildAndExpand(4)
                .toUri();

        RequestBuilder req = MockMvcRequestBuilders.get(fullUri);

        MvcResult res = this.mvc.perform(req).andReturn();
        String rawJsonBody = res.getResponse().getContentAsString();

        fail("Not Yet Implemented");
    }

}