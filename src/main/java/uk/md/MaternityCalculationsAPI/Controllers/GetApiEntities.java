package uk.md.MaternityCalculationsAPI.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetApiEntities {

    // Un-Parsed Response: More useful to keep it this way in case of error 404
    public HttpResponse<String> getAdmission(int id) throws IOException, InterruptedException {
        Admission admission;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions/" + id))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public Boolean checkIfAdmissionNotFound(HttpResponse<String> response){
        return response != null && response.statusCode() == 404;
    }

    // Requires a Success Status: 200 to give a model
    // 1. Use Get Admission 2. Check Response Header. 3. If Success: Parse
    public Admission parseAdmission(HttpResponse<String> response){
        if (response != null && response.statusCode() == 200){
            String rawJSON = response.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            try {
                // Returns the Object as Intended Object
                return mapper.readValue(rawJSON, new TypeReference<Admission>() {});
            }
            catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
