package uk.md.MaternityCalculationsAPI.UnitTests.FunctionalTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import uk.md.MaternityCalculationsAPI.BusinessLogic.BusiestDayLogic;
import uk.md.MaternityCalculationsAPI.Controllers.GetApiLists;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusiestDayLogicTest {

    // Mocking HttpResponse using Mockito
    // Expecting Wednesday
    @Test
    void test_calculate_frequency_of_days_1_busiest_day() throws JsonProcessingException {
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("Wednesday");

        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Picking 5 Items: 3 Admission Dates on Wednesdays and 2 on any other day
        String rawJsonBody = "[  { \"id\": 1, \"admissionDate\": \"2023-04-05T16:45:00\", \"dischargeDate\": \"2025-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-10T16:45:00\", \"dischargeDate\": \"2023-12-05T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-24T16:45:00\", \"dischargeDate\": \"2024-05-20T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-08T16:45:00\", \"dischargeDate\": \"2024-08-14T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-14T16:45:00\", \"dischargeDate\": \"2024-08-14T23:56:00\", \"patientID\": 2 } ]";

        // Stubbing out get Employee List to return the dummy data
        when(dummyHttpResponse.statusCode()).thenReturn(200);
        when(dummyHttpResponse.body()).thenReturn(rawJsonBody);
        HttpHeaders headers = HttpHeaders.of(Map.of("Content-Type", List.of("application/json")), (s, s2) -> true);
        Mockito.when(dummyHttpResponse.headers()).thenReturn(headers);

        GetApiLists listHttpHandler = new GetApiLists();
        List<Admission> allAdmissions = listHttpHandler.parseAdmissionList(dummyHttpResponse);
        
        // Act
        BusiestDayLogic logicObj = new BusiestDayLogic();
        List<String> actualResult = logicObj.calculateBusiestDay(allAdmissions);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_calculate_frequency_of_days_7_busiest_day() {
        Assertions.fail("Busiest Day Logic: Not Implemented");
    }
    @Test
    void test_calculate_frequency_of_days_3_busiest_day() {
        Assertions.fail("Busiest Day Logic: Not Implemented");
    }
    @Test
    void test_admissions_before_discharge_checker(){
        Assertions.fail("Busiest Day Logic: Not Implemented");
    }
}
