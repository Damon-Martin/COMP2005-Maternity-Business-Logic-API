package uk.md.MaternityCalculationsAPI.Models.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

// A Single Admission Model: Used in the Received List of Admission Models by the Maternity '/Admissions' Endpoint
public class Admission {
    // IDs to Map to the Patient and the id
    public Integer id;
    public Integer patientID;

    // Instant for Dates: Instant.Parse(stringDate) || Instant.toString()
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime admissionDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime dischargeDate;

    // Getter & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public LocalDateTime getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDateTime admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDateTime dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
