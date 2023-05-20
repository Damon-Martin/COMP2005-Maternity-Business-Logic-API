package uk.md.MaternityCalculationsAPI.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("api/")
@Api(value = "Responsible for all business logic calculations")
public class AdmissionsController {
    private GetApiLists _httpHandler = new GetApiLists();

    @GetMapping("PatientsSeenByStaff")
    @ApiOperation(value = "Based on EmployeeID: Returns a list unique patient IDs that have visited the staff")
    public String PatientsSeenByStaff() {
        return null;
    }

    @GetMapping("DischargedQuick")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns a list of patients who are discharged within 3 days of admission")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Bad Request: Error Calculating Quick Discharge Patients List"),
                    @ApiResponse(code = 200, message = "Success: List of Patients discharged within 3 days")
            })
    public String DischargedQuick() throws IOException, InterruptedException {
        HttpResponse<String> res = _httpHandler.getEmployeesList();

        // Checking if Successful or Error
        if (res.statusCode() == 200) {
            return "Contacted";
        }
        else if (res.statusCode() == 404) {
            return "Not Found";
        }
        return "Error: Failed Contacting Maternity API";
    }

    @GetMapping("AvgDurationByStaff")
    @ApiOperation(value = "Based on EmployeeID, Returns the mean calculated avg duration of recovery time/stay of all their patients")
    public String AvgDurationByStaff() {
        return null;
    }

    // Provide Week: Returns the busiest day
    @GetMapping("BusiestDayOfWeek")
    @ApiOperation(value = "Returns the calculated avg busiest day of the week",
            notes = "Based on the Maternity '/Admissions' Endpoint: Loops through the List and Calculates the frequency of days in a hash table (e.g 'Mon: 4, Tues:2, Wed: 10...'). Then Returns the busiest day.")
    public String BusiestDayOfWeek() {
        return null;
    }
}
