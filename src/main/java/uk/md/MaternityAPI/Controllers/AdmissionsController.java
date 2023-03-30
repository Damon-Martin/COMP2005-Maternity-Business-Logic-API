package uk.md.MaternityAPI.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmissionsController {
    @GetMapping("api/Admissions/DischargedQuick")
    public String dischargedQuick() {
        return ("JSON of Discharged Patients");
    }
}
