package uk.md.MaternityCalculationsAPI.Controllers;

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
import java.util.List;

// Intended to Perform Get Requests and return Expected JSON
// Can Be Mocked
public class GetApiLists {


    // Physically can't have an empty string. If the List is empty the res body will be '[]'
    public List<Admission> getAdmissionsList() throws IOException, InterruptedException {
        List<Admission> admissions;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null && response.statusCode() == 200) {
            String rawJSON = response.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            admissions = mapper.readValue(rawJSON, new TypeReference<List<Admission>>() {
            });

            return admissions;
        }
        return null;
    }

    // If it's Empty we would have an Empty Array. Therefore, can't be null.
    public List<Employee> getEmployeesList() throws IOException, InterruptedException {
        List<Employee> employees;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Employees"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null && response.statusCode() == 200) {
            String rawJSON = response.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(rawJSON, new TypeReference<List<Employee>>() {
            });

            return employees;
        }
        // Bad Request: If there were no items in the array we would get ''. An Empty Array.
        return null;
    }

    public List<Allocation> getAllocationsList() throws IOException, InterruptedException {
        List<Allocation> allocations;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null && response.statusCode() == 200) {
            String rawJSON = response.body();

            // Mapping to the Deserialized Object
            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            allocations = mapper.readValue(rawJSON, new TypeReference<List<Allocation>>() {
            });

            return allocations;
        }

        return null;
    }

    // If it's Empty we would have an Empty Array. Therefore, can't be null.
    public List<Patient> getPatientsList() throws IOException, InterruptedException {
        List<Patient> patients;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Patients"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null && response.statusCode() == 200) {
            String rawJSON = response.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            patients = mapper.readValue(rawJSON, new TypeReference<List<Patient>>() {
            });

            return patients;
        }

        return null;
    }
}

