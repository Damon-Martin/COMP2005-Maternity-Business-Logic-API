package uk.md.MaternityAPI.UnitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdmissionsControllerTest {
    // Initialising Tests: Reset Each Test to these defaults
    @BeforeEach
    void initRepeat() {
    }

    // Check if the output is JSON with Key Values
    @Test
    void dischargedQuickReturnsJSON() {
        // Attempts to parse the Response to a HashTable using

        // If: Can be parsed into a HashTable then it passes

        // Else: Fails (Just a normal String)
        assertEquals(2,4);
    }
}