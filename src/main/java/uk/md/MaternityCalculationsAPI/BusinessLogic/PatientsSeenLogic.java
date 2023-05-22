package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Models.DischargedQuick;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;

import java.util.List;

public class PatientsSeenLogic {

    public List<DischargedQuick> getPatientsByEmployeeID(int EmployeeID, List<Allocation> allAllocations, List<Admission> allAdmissions, List<Patient> allPatients) {
        // We have all data needed
        // For Each Allocation (Looping): If EmployeeID == Allocation.getEmployeeID(), Go to this Admission and view patients. Then, Build list of Patients

        // Filter Patients to be unique

        return null;
    }
}
