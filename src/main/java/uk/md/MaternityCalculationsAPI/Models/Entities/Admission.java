package uk.md.MaternityCalculationsAPI.Models.Entities;

import java.time.Instant;
import java.util.Date;

// A Single Admission Model: Used in the Received List of Admission Models by the Maternity '/Admissions' Endpoint
public class Admission {
    // IDs to Map to the Patient and the id
    public Integer id;
    public Integer patientID;

    // Instant for Dates: Instant.Parse(stringDate) || Instant.toString()
    public Instant admissionDate;
    public Instant dischargeDate;

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

    public Instant getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Instant admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Instant getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Instant dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
