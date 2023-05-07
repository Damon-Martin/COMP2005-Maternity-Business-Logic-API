package uk.md.MaternityCalculationsAPI.Controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Employee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Intended to Perform Get Requests and return Expected JSON
// Can Be Mocked
public class GetApiJson {
    HttpClient httpClient = HttpClient.newBuilder().build();

    // Physically can't have an empty string. If the List is empty the res body will be '[]'
    public List<Admission> getAdmissionsListRawJSON() throws IOException, InterruptedException {
        List<Admission> admissions;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String rawJSON = response.body();

        // Mapping to the Deserialized Object
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        admissions = mapper.readValue(rawJSON, new TypeReference<List<Admission>>() {});

        return admissions;
    }

    // If it's Empty we would have an Empty Array. Therefore, can't be null.
    public List<Employee> getEmployeeList() throws IOException, InterruptedException {
        List<Employee> employees;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Employees"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String rawJSON = response.body();

        // Mapping to the Deserialized Object
        ObjectMapper mapper = new ObjectMapper();
        employees = mapper.readValue(rawJSON, new TypeReference<List<Employee>>() {});

        return employees;
    }

}

