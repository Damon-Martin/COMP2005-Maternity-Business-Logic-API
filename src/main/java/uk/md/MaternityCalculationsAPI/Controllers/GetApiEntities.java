package uk.md.MaternityCalculationsAPI.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetApiEntities {
    // If it's Empty we would have an Empty Array. Therefore, can't be null.
    public HttpResponse<String> getPatientById(Integer id) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Patients/" + id))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public Patient parseSinglePatient(HttpResponse<String> res) throws JsonProcessingException {
        Patient patients = new Patient();
        if (res != null && res.statusCode() == 200) {
            String rawJSON = res.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            patients = mapper.readValue(rawJSON, new TypeReference<Patient>() {
            });
        }
        return patients;
    }

    public HttpResponse<String> getAdmissionById(Integer id) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions/" + id))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public Admission parseSingleAdmission(HttpResponse<String> res) throws JsonProcessingException {
        Admission admission = new Admission();
        if (res != null && res.statusCode() == 200) {
            String rawJSON = res.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            admission = mapper.readValue(rawJSON, new TypeReference<Admission>() {
            });
        }
        return admission;
    }

    public HttpResponse<String> getEmployeeById(Integer id) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Employees/" + id))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
