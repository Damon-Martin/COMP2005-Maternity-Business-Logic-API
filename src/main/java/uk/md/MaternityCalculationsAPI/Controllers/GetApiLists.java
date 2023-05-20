package uk.md.MaternityCalculationsAPI.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Employee;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

// Intended to Perform Get Requests and return Expected JSON
// Can Be Mocked
public class GetApiLists {


    // Physically can't have an empty string. If the List is empty the res body will be '[]'
    public HttpResponse<String> getAdmissionsList() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions"))
                .GET()
                .build();

        // Returning the HttpResponse: Contains Body + Header
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // Unit Test JsonProcessing Exception
    public List<Admission> parseAdmissionList(HttpResponse<String> res) throws JsonProcessingException {

        List<Admission> admissions = new ArrayList<Admission>();
        if (res != null && res.statusCode() == 200) {
            String rawJSON = res.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            admissions = mapper.readValue(rawJSON, new TypeReference<List<Admission>>() {
            });
        }
        return admissions;
    }

    public HttpResponse<String> getEmployeesList() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Employees"))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Employee> parseEmployeeList(HttpResponse<String> res) throws JsonProcessingException {
        List<Employee> employees = new ArrayList<Employee>();
        if (res != null && res.statusCode() == 200) {
            String rawJSON = res.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(rawJSON, new TypeReference<List<Employee>>() {
            });
        }
        return employees;
    }

    public HttpResponse<String> getAllocationsList() throws IOException, InterruptedException {
        List<Allocation> allocations;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations"))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Allocation> parseAllocationList(HttpResponse<String> res) throws JsonProcessingException {
        List<Allocation> allocations = new ArrayList<Allocation>();
        if (res != null && res.statusCode() == 200) {
            String rawJSON = res.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            allocations = mapper.readValue(rawJSON, new TypeReference<List<Allocation>>() {
            });
        }
        return allocations;
    }
    // If it's Empty we would have an Empty Array. Therefore, can't be null.
    public HttpResponse<String> getPatientsList() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Patients"))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Patient> parsePatientsList(HttpResponse<String> res) throws JsonProcessingException {
        List<Patient> patients = new ArrayList<Patient>();
        if (res != null && res.statusCode() == 200) {
            String rawJSON = res.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            patients = mapper.readValue(rawJSON, new TypeReference<List<Patient>>() {
            });
        }
        return patients;
    }
}

