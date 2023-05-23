package uk.md.MaternityCalculationsAPI.BusinessLogic;

import uk.md.MaternityCalculationsAPI.Models.Entities.Admission;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Which Day has most Admissions
public class BusiestDayLogic {


    // Testable Calculation Functions Here
    // Pass in
    public Map<String,Integer> calculateFrequencyOfDays(List<Admission> allAllocations) {
        // List of weeks as list of enums
        List<DayOfWeek> allWeekDaysInAllocations = allAllocations.stream()
                .map(myObj -> myObj.admissionDate.getDayOfWeek())
                .collect(Collectors.toList());

        // Day Frequency
        Map<String, Integer> dayFrequency = allWeekDaysInAllocations.stream()
                .map(DayOfWeek::name)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));


        return dayFrequency;
    }


    // Merges all testable functions for ease of use
    public List<String> calculateBusiestDay(List<Admission> allAdmissionsBeforeFilter) {
        List<String> busiestDay = new ArrayList<String>();

        // Filter Bad Date Orders
        List<Admission> allAdmissions = new ArrayList<Admission>();
        allAdmissionsBeforeFilter.forEach( admission -> {
            DischargedQuickLogic logicObj = new DischargedQuickLogic();
            Boolean datesOrder = logicObj.dateOrderCorrect(admission.admissionDate, admission.dischargeDate);
            if (datesOrder) {
                allAdmissions.add(admission);
            }
        });

        // All Logic here
        Map<String, Integer> weekdayFrequency = calculateFrequencyOfDays(allAdmissions);

        // Look for the max frequency in the map
        // if there are multiple with that value. Then return all.

        final Integer[] maxFreq = {0}; // Holds a single Integer: Work around for lambda expression
        weekdayFrequency.forEach((weekday, freq) -> {
            if (freq > maxFreq[0]) {
                maxFreq[0] = freq;
                busiestDay.clear();
                busiestDay.add(weekday);
            } else if (freq.equals(maxFreq[0])) {
                busiestDay.add(weekday);
            }
        });

        return busiestDay;
    }
}
