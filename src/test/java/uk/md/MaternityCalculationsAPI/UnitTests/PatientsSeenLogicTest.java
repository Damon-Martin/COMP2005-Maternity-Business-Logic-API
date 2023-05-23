package uk.md.MaternityCalculationsAPI.UnitTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.md.MaternityCalculationsAPI.BusinessLogic.BusiestDayLogic;
import uk.md.MaternityCalculationsAPI.BusinessLogic.PatientsSeenLogic;
import uk.md.MaternityCalculationsAPI.Controllers.GetApiLists;
import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;
import uk.md.MaternityCalculationsAPI.Models.Entities.Allocation;
import uk.md.MaternityCalculationsAPI.Models.Entities.Patient;
import uk.md.MaternityCalculationsAPI.Models.PatientCustom;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatientsSeenLogicTest {

    // Dummy Instance: Default
    // Info(For Default Case): Allocations has 5 items
    // Employee 2 attends Allocations 1 & 2 & 3
    // Allocations 1 & 3 are the same patient. However, the rest are diff patients (3 other)
    List<Allocation> dummyDataAllocations = new ArrayList<Allocation>();
    List<Admission> dummyDataAdmissions = new ArrayList<Admission>();
    List<Patient> dummyDataPatients = new ArrayList<Patient>();

    // Mocking Responses & Parsing into Lists of Obj
    public PatientsSeenLogicTest() throws JsonProcessingException {
        // Arange
        HttpResponse<String> dummyAdmissionsHttpResponse = mock(HttpResponse.class);
        HttpResponse<String> dummyAllocationsHttpResponse = mock(HttpResponse.class);
        HttpResponse<String> dummyPatientsHttpResponse = mock(HttpResponse.class);

        // 3 Allocations total
        // Emp1: P1 admissions 2x
        // Emp2: P1 admissions: 1x, P2 admissions 1x, P3 admissions 1x

        // 3 Patients total (P1: 3 Admissions, P2: 1 Admission, P3: 1 Admission)
        String rawPatientsJson = "[ { \"id\": 1, \"surname\": \"Smith\", \"forename\": \"Jack\", \"nhsNumber\": \"999234\" }, { \"id\": 2, \"surname\": \"Thomas\", \"forename\": \"Martin\", \"nhsNumber\": \"892134\" } , { \"id\": 3, \"surname\": \"Chadwick\", \"forename\": \"Alex\", \"nhsNumber\": \"829321\" } ]";

        // 5 Admissions total (5 Allocations to 2 Employees)
        // Emp1: P1 admissions 2x
        // Emp2: P1 admissions: 1x, P2 admissions 1x, P3 admissions 1x
        String rawAdmissionJson = "[ { \"id\": 1, \"admissionDate\": \"2020-11-28T16:45:00\", \"dischargeDate\": \"2020-11-28T23:56:00\", \"patientID\": 1 }, { \"id\": 2, \"admissionDate\": \"2020-12-07T22:14:00\", \"dischargeDate\": \"2021-01-05T19:15:05\", \"patientID\": 1 }, { \"id\": 3, \"admissionDate\": \"2020-09-23T21:50:00\", \"dischargeDate\": \"2020-09-27T09:56:00\", \"patientID\": 2 }, { \"id\": 4, \"admissionDate\": \"2021-09-23T21:50:00\", \"dischargeDate\": \"2021-09-27T09:56:00\", \"patientID\": 1 }, { \"id\": 5, \"admissionDate\": \"2023-09-23T21:50:00\", \"dischargeDate\": \"2023-09-27T09:56:00\", \"patientID\": 3 } ]";

        // Allocations: Employees to Admissions
        String rawAllocationsJson = "[ { \"id\": 1, \"admissionID\": 1, \"employeeID\": 1, \"startTime\": \"2020-11-28T16:45:00\", \"endTime\": \"2020-11-28T23:56:00\"} ,  { \"id\": 2, \"admissionID\": 2, \"employeeID\": 1, \"startTime\": \"2020-12-07T22:14:00\", \"endTime\": \"2021-01-05T19:15:05\"}, { \"id\": 3, \"admissionID\": 3, \"employeeID\": 2, \"startTime\": \"2020-09-23T21:50:00\", \"endTime\": \"2020-09-27T09:56:00\"} ,  { \"id\": 4, \"admissionID\": 4, \"employeeID\": 1, \"startTime\": \"2021-09-23T21:50:00\", \"endTime\": \"2021-09-27T09:56:00\"}  ,{ \"id\": 5, \"admissionID\": 5, \"employeeID\": 2, \"startTime\": \"2023-09-23T21:50:00\", \"endTime\": \"2023-09-27T09:56:00\"} ]";

        // Parsing & Saving using mock HttpRequests
        // Stubbing out get Employee List to return the dummy data
        when(dummyAdmissionsHttpResponse.statusCode()).thenReturn(200);
        when(dummyAdmissionsHttpResponse.body()).thenReturn(rawAdmissionJson);
        HttpHeaders headers = HttpHeaders.of(Map.of("Content-Type", List.of("application/json")), (s, s2) -> true);
        Mockito.when(dummyAdmissionsHttpResponse.headers()).thenReturn(headers);

        when(dummyAllocationsHttpResponse.statusCode()).thenReturn(200);
        when(dummyAllocationsHttpResponse.body()).thenReturn(rawAllocationsJson);
        Mockito.when(dummyAllocationsHttpResponse.headers()).thenReturn(headers);

        when(dummyPatientsHttpResponse.statusCode()).thenReturn(200);
        when(dummyPatientsHttpResponse.body()).thenReturn(rawPatientsJson);
        Mockito.when(dummyPatientsHttpResponse.headers()).thenReturn(headers);


        // Parsing Responses into List<Obj>
        GetApiLists listHttpHandler = new GetApiLists();
        this.dummyDataAdmissions = listHttpHandler.parseAdmissionList(dummyAdmissionsHttpResponse);
        this.dummyDataPatients = listHttpHandler.parsePatientsList(dummyPatientsHttpResponse);
        this.dummyDataAllocations = listHttpHandler.parseAllocationList(dummyAllocationsHttpResponse);

    }

    // Mock HttpResponse returns dummy data when called. There are duplicate Patients for Allocation in this example.
    // the unfiltered list creator is not responsible for removing bad values
    //
    @Test
    void unfiltered_list_employee1_result_size_is_3(){
        //Arrange (Prepped in Constructor)
        List<PatientCustom> result = new ArrayList<PatientCustom>();
        int employeeId = 1; // Looking for 1

        // Act
        PatientsSeenLogic logicObj = new PatientsSeenLogic();
        result = logicObj.getNotFilteredPatientsByEmployeeID(employeeId, dummyDataAllocations, dummyDataAdmissions, dummyDataPatients);

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void unfiltered_list_employee2_result_size_is_2(){
        //Arrange (Prepped in Constructor)
        List<PatientCustom> result = new ArrayList<PatientCustom>();
        int employeeId = 2; // Looking for 1

        // Act
        PatientsSeenLogic logicObj = new PatientsSeenLogic();
        result = logicObj.getNotFilteredPatientsByEmployeeID(employeeId, dummyDataAllocations, dummyDataAdmissions, dummyDataPatients);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void test_filtering_duplicates() {
        // Arrange: Passing in 3 Patient Objects
        List<PatientCustom> unfiltered = new ArrayList<PatientCustom>();
        // Expecting 2 Patients: PatientID 10 & PatientID 5
        List<PatientCustom> expected = new ArrayList<PatientCustom>();

        // Patients 1 & 3 are the same (Same ID)
        PatientCustom patient1 = new PatientCustom();
        PatientCustom patient2 = new PatientCustom();
        PatientCustom patient3 = new PatientCustom();

        patient1.setPatientID(10);
        patient1.setForename("David");
        patient1.setForename("Jackson");
        patient1.setNhsNumber("111111");

        patient3.setPatientID(10);
        patient3.setForename("David");
        patient3.setForename("Jackson");
        patient3.setNhsNumber("111111");

        patient2.setPatientID(5);
        patient2.setForename("Sarah");
        patient2.setForename("James");
        patient2.setNhsNumber("222333");

        unfiltered.add(patient1);
        unfiltered.add(patient2);
        unfiltered.add(patient3);

        expected.add(patient1);
        expected.add(patient2);


        // Act
        PatientsSeenLogic logicObj = new PatientsSeenLogic();
        List<PatientCustom> filteredList = logicObj.filter(unfiltered);

        // Assert
        Assertions.assertEquals(expected, filteredList);
    }
}
