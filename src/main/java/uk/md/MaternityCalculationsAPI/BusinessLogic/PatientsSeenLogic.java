package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Controllers.GetApiEntities;
import uk.md.MaternityCalculationsAPI.Models.PatientCustom;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PatientsSeenLogic {


    public List<PatientCustom> filter(List<PatientCustom> unfilteredList) {
        // Filter Duplicate Patients

        return unfilteredList;
    }

    public List<PatientCustom> getNotFilteredPatientsByEmployeeID(int EmployeeID, List<Allocation> allAllocations, List<Admission> allAdmissions, List<Patient> allPatients) {
        // We have all data needed
        // For Each Allocation (Looping): If EmployeeID == Allocation.getEmployeeID(), Go to this Admission and view patients. Then, Build list of Patients
        List<PatientCustom> unfilteredList = new ArrayList<PatientCustom>();
        allAllocations.forEach( Allocation -> {
            if (EmployeeID == Allocation.getEmployeeID()) {
                // Grab the current Admission from Allocation
                GetApiEntities httpReqObj = new GetApiEntities();

                try {
                    HttpResponse<String> admissionById = httpReqObj.getAdmissionById(Allocation.getAdmissionID());
                    PatientCustom newPatient = new PatientCustom();
                    if (admissionById.statusCode() == 200) {
                        Admission currentAdmission = httpReqObj.parseAdmission(admissionById);

                        int PatientID = currentAdmission.getPatientID();

                        HttpResponse<String> patientRes= httpReqObj.getPatientById(PatientID);

                        if (patientRes.statusCode() == 200) {
                            // Parsing List
                            Patient currentPatient = httpReqObj.parsePatientById(patientRes);
                            // Propagate/Copy values
                            newPatient.setPatientID(currentAdmission.getPatientID());
                            newPatient.setNhsNumber(currentPatient.getNhsNumber());
                            newPatient.setForename(currentPatient.getForename());
                            newPatient.setSurname(currentPatient.getForename());

                            unfilteredList.add(newPatient);
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return unfilteredList;
    }

    // Use this one for Controller
    public List<PatientCustom> getFilteredPatientsByEmployeeID(int EmployeeID, List<Allocation> allAllocations, List<Admission> allAdmissions, List<Patient> allPatients) {
        // We have all data needed

        // Get Unfiltered List
        List<PatientCustom> unfilteredList = getNotFilteredPatientsByEmployeeID(EmployeeID, allAllocations, allAdmissions, allPatients);

        // Filter Patients to be unique
        return filter(unfilteredList);
    }
}
