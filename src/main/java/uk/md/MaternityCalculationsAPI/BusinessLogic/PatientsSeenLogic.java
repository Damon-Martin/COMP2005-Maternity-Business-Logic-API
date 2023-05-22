package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Models.PatientCustom;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.util.List;

public class PatientsSeenLogic {


    public List<PatientCustom> filter(List<PatientCustom> unfilteredList) {

        return null;
    }

    public List<PatientCustom> getNotFilteredPatientsByEmployeeID(int EmployeeID, List<Allocation> allAllocations, List<Admission> allAdmissions, List<Patient> allPatients) {
        // We have all data needed
        // For Each Allocation (Looping): If EmployeeID == Allocation.getEmployeeID(), Go to this Admission and view patients. Then, Build list of Patients

        return null;
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
