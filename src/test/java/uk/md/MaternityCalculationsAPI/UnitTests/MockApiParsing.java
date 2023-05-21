package uk.md.MaternityCalculationsAPI.UnitTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import uk.md.MaternityCalculationsAPI.Controllers.GetApiLists;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;

import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.reflect.TypeToken;

public class MockApiParsing {

    // Using Dummy data and a Stubbing
    // Using Acceptable Parsable Data
    // Ignore Warning for mock: It Mocks correctly the type correctly when used (Doesn't like implicit <String>)
    @Test
    void test_get_admissions_parsing_acceptable_not_null() throws IOException, InterruptedException {
        // Arrange
        // Creating Dummy Data: Using CSV files for raw JSON Bodies
        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Stubbing out get Employee List to return the dummy data
        when(dummyHttpResponse.statusCode()).thenReturn(200);
        when(dummyHttpResponse.body()).thenReturn("[ { \"id\": 1, \"admissionDate\": \"2020-11-28T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 2 }, { \"id\": 2, \"admissionDate\": \"2020-12-07T22:14:00\", \"dischargeDate\": \"0001-01-01T00:00:00\", \"patientID\": 1 }, { \"id\": 3, \"admissionDate\": \"2021-09-23T21:50:00\", \"dischargeDate\": \"2021-09-27T09:56:00\", \"patientID\": 2 } ]");
        HttpHeaders headers = HttpHeaders.of(Map.of("Content-Type", List.of("application/json")), (s, s2) -> true);
        Mockito.when(dummyHttpResponse.headers()).thenReturn(headers);

        // Act
        // Testing the Parsing using the dummy response
        GetApiLists listHttpHandler = new GetApiLists();
        List<Admission> allAdmissions = listHttpHandler.parseAdmissionList(dummyHttpResponse);

        // Assertions
        Assertions.assertNotNull(allAdmissions);
    }

    // Checks if not null & len is 0
    @Test
    void test_get_admissions_parsing_acceptable_empty_and_len_zero() throws IOException, InterruptedException {
        // Arrange
        // Creating Dummy Data: Using CSV files for raw JSON Bodies
        HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

        // Stubbing out get Employee List to return the dummy data
        when(dummyHttpResponse.statusCode()).thenReturn(200);
        when(dummyHttpResponse.body()).thenReturn("[]");
        HttpHeaders headers = HttpHeaders.of(Map.of("Content-Type", List.of("application/json")), (s, s2) -> true);
        Mockito.when(dummyHttpResponse.headers()).thenReturn(headers);

        // Act
        // Testing the Parsing using the dummy response
        GetApiLists listHttpHandler = new GetApiLists();
        List<Admission> allAdmissions = listHttpHandler.parseAdmissionList(dummyHttpResponse);

        // Assertions
        Assertions.assertNotNull(allAdmissions);
        Assertions.assertEquals(0, allAdmissions.size());
    }

    @Test
    void test_get_admissions_parsing_invalid_data_throws_json_parsing_error() {

        Assertions.assertThrows(JsonProcessingException.class, () -> {
            // Arrange
            // Creating Dummy Data: Using CSV files for raw JSON Bodies
            HttpResponse<String> dummyHttpResponse = mock(HttpResponse.class);

            // Stubbing out get Employee List to return the dummy data
            when(dummyHttpResponse.statusCode()).thenReturn(200);
            when(dummyHttpResponse.body()).thenReturn("[  { \"id\": 3, \"admissionDate\": 10, \"dischargeDate\": 10, \"patientID\": 10 } ] ,{ \"id\": 3, \"admissionDate\": 10, \"dischargeDate\": 10, \"patientID\": 10 } ]");
            HttpHeaders headers = HttpHeaders.of(Map.of("Content-Type", List.of("application/json")), (s, s2) -> true);
            Mockito.when(dummyHttpResponse.headers()).thenReturn(headers);


            // Act
            // Testing the Parsing using the dummy response
            GetApiLists listHttpHandler = new GetApiLists();
            listHttpHandler.parseAdmissionList(dummyHttpResponse);
        });
    }
    
}