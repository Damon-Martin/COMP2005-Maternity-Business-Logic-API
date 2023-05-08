package uk.md.MaternityCalculationsAPI.Controllers;

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

    public Admission getAdmission(int id) throws IOException, InterruptedException {
        Admission admission;
        HttpClient httpClient = HttpClient.newBuilder().build();

        // Making Request & Performing GET Req
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions/" + id))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response != null && response.statusCode() == 200){
            String rawJSON = response.body();

            // Mapping to the Deserialized Object
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            admission = mapper.readValue(rawJSON, new TypeReference<Admission>() {});

            return admission;
        }

        return null;
    }
}
