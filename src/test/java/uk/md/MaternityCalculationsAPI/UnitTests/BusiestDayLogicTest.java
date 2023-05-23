package uk.md.MaternityCalculationsAPI.UnitTests;

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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusiestDayLogicTest {

    // Mocking HttpResponse using Mockito
    // Expecting Wednesday
    @Test
    void test_calculate_frequency_of_days_has_1_busiest_day() throws JsonProcessingException {
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("WEDNESDAY");

        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Picking 5 Items: 3 Admission Dates on Wednesdays and 2 on any other day
        String rawJsonBody = "[  { \"id\": 1, \"admissionDate\": \"2023-04-05T16:45:00\", \"dischargeDate\": \"2025-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-12T16:45:00\", \"dischargeDate\": \"2023-12-05T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-19T16:45:00\", \"dischargeDate\": \"2024-05-20T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-08T16:45:00\", \"dischargeDate\": \"2024-08-14T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-14T16:45:00\", \"dischargeDate\": \"2024-08-14T23:56:00\", \"patientID\": 2 } ]";

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

    // 7 inputs, 3 busiest days
    @Test
    void test_calculate_frequency_of_days_has_3_busiest_day_large_data() throws JsonProcessingException {
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("FRIDAY");
        expectedResult.add("TUESDAY");
        expectedResult.add("SATURDAY");

        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Picking 5 Items: 3 Admission busiest days (2 incidents of each + a random other day)
        String rawJsonBody = "[ { \"id\": 1, \"admissionDate\": \"2020-11-28T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2020-11-10T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 } , { \"id\": 1, \"admissionDate\": \"2020-11-20T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2020-11-28T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2020-11-10T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 } , { \"id\": 1, \"admissionDate\": \"2020-11-20T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2020-11-01T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }]";

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

    // If we have the orders of admission and discharged messed up. Remove that.
    @Test
    void test_admissions_before_discharge_is_filtered() throws JsonProcessingException {
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("THURSDAY");
        expectedResult.add("SATURDAY");

        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Picking 5 Items: 3 Admission Dates on Wednesdays and 2 on any other day
        String rawJsonBody = "[ { \"id\": 1, \"admissionDate\": \"2020-11-28T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 2, \"admissionDate\": \"2020-12-07T22:14:00\", \"dischargeDate\": \"0001-01-01T00:00:00\", \"patientID\": 1 }, { \"id\": 3, \"admissionDate\": \"2021-09-23T21:50:00\", \"dischargeDate\": \"2021-09-27T09:56:00\", \"patientID\": 2 } ]";

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
    void test_admissions_to_weekday_frequency_map() throws JsonProcessingException {
        Map<String, Integer> expectedResult = new Hashtable<String, Integer>();
        expectedResult.put("WEDNESDAY", 3);
        expectedResult.put("FRIDAY", 1);
        expectedResult.put("SATURDAY", 1);

        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Picking 5 Items: 3 Admission Dates on Wednesdays and 2 on any other day
        String rawJsonBody = "[  { \"id\": 1, \"admissionDate\": \"2023-04-05T16:45:00\", \"dischargeDate\": \"2025-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-12T16:45:00\", \"dischargeDate\": \"2023-12-05T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-19T16:45:00\", \"dischargeDate\": \"2024-05-20T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-08T16:45:00\", \"dischargeDate\": \"2024-08-14T23:56:00\", \"patientID\": 2 }, { \"id\": 1, \"admissionDate\": \"2023-04-14T16:45:00\", \"dischargeDate\": \"2024-08-14T23:56:00\", \"patientID\": 2 } ]";

        // Stubbing out get Employee List to return the dummy data
        when(dummyHttpResponse.statusCode()).thenReturn(200);
        when(dummyHttpResponse.body()).thenReturn(rawJsonBody);
        HttpHeaders headers = HttpHeaders.of(Map.of("Content-Type", List.of("application/json")), (s, s2) -> true);
        Mockito.when(dummyHttpResponse.headers()).thenReturn(headers);

        GetApiLists listHttpHandler = new GetApiLists();
        List<Admission> allAdmissions = listHttpHandler.parseAdmissionList(dummyHttpResponse);

        // Act
        BusiestDayLogic logicObj = new BusiestDayLogic();
        Map<String,Integer> actualResult = logicObj.calculateFrequencyOfDays(allAdmissions);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
