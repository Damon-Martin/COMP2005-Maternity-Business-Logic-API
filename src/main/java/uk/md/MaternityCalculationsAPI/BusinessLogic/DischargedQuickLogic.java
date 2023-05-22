package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Controllers.GetApiEntities;
import uk.md.MaternityCalculationsAPI.Models.PatientCustom;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DischargedQuickLogic {


    // Checks how many days have passed
    public Integer calculateDaysInHospital(LocalDateTime startDate, LocalDateTime endDate){
        long daysPassedLong = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
        return (int) daysPassedLong;
    }

    // Checks if the Days are Less than or Equal to 3 days
    public Boolean isQuick(Integer daysAdmitted){
        return daysAdmitted <= 3;
    }

    // Checks if the JSON Admission Data is Valid Data
    public Boolean isStartDateBeforeEndDate(LocalDateTime startDate, LocalDateTime endDate){
        return startDate.isBefore(endDate);
    }

    // Returns Filtered List
    public List<PatientCustom> filter_patient_duplicates(List<PatientCustom> unfiltered_patient_li) {
        List<Integer> listOfPatientIDs = new ArrayList<Integer>();
        List<PatientCustom> temp = new ArrayList<PatientCustom>();
        unfiltered_patient_li.forEach( DischargedQuick -> {
            // New Patient: If true
            if (!listOfPatientIDs.contains(DischargedQuick.getPatientID())){
                listOfPatientIDs.add(DischargedQuick.getPatientID());
                temp.add(DischargedQuick);
            }
            // Patient Already Exists: Ignore it & it won't add the duplicate
        });

        return temp;
    }

    // We can mock allAdmissions for Testing
    // This is a Component: It combines multiple Units together
    public List<PatientCustom> calculateDischargedQuick(List<Admission> allAdmissions)  {
        List<PatientCustom> dischargedQuickList = new ArrayList<PatientCustom>();
        allAdmissions.forEach(Admission -> {
            LocalDateTime currentStartDate = Admission.getAdmissionDate();
            LocalDateTime currentEndDate = Admission.getDischargeDate();

            Integer daysAdmitted = calculateDaysInHospital(currentStartDate, currentEndDate);

            // Check start is before End Date
            if (isQuick(daysAdmitted) && isStartDateBeforeEndDate(currentStartDate, currentEndDate)) {
                // Perform Calculations & Build Object
                PatientCustom fastPatientCase = new PatientCustom();
                fastPatientCase.setPatientID(Admission.getPatientID());

                // Fetch Patient Name
                GetApiEntities EntityHandler = new GetApiEntities();
                try {
                    HttpResponse<String> res = EntityHandler.getPatientById(fastPatientCase.getPatientID());
                    Patient currentPatient = EntityHandler.parsePatientById(res);

                    fastPatientCase.setNhsNumber(currentPatient.getNhsNumber());
                    fastPatientCase.setForename(currentPatient.getForename());
                    fastPatientCase.setSurname(currentPatient.getSurname());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Adding To List
                dischargedQuickList.add(fastPatientCase);
            }
        });


        // Filter dischargedQuickList Patients to only have 1 instance of each PatientID
        return filter_patient_duplicates(dischargedQuickList);
    }
}
