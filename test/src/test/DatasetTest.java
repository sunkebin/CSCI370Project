package src.test;

import org.junit.jupiter.api.Test;
import src.Dataset;
import src.Patient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatasetTest {

    Patient newPatient1;
    Patient newPatient2;
    Patient newPatient3;
    Patient newPatient4;
    Patient newPatient5;
    Patient newPatient6;
    List<Patient> testPatientData;

    public DatasetTest() {
        newPatient1 = new Patient(59, 2, 32.1, 100, 0, 157, 93.0, 37.5, 4.0, 4.859, 87);
        newPatient2 = new Patient(48, 1, 21.6, 87, 193, 103.2, 70.0, 3.00, 3.891, 69, 75);
        newPatient3 = new Patient(72, 2, 30.5, 93.0, 156, 93.6, 41.0, 4.00, 4.672, 85, 141);
        newPatient4 = new Patient(24, 1, 25.3, 84, 198, 131.4, 40, 5, 4.890, 89, 206);
        newPatient5 = new Patient(50, 1, 23, 101, 192, 125.4, 52, 4, 4.920, 80, 135);
        newPatient6 = new Patient(23, 1, 22.6, 89, 139, 64.8, 61, 2, 4.189, 68, 97);

        testPatientData = new LinkedList<>();
        testPatientData.add(newPatient1);
        testPatientData.add(newPatient2);
        testPatientData.add(newPatient3);
        testPatientData.add(newPatient4);
        testPatientData.add(newPatient5);
        testPatientData.add(newPatient6);
    }

    @Test
    void validatePatientData() throws Exception {
        // Create a test patient
        Patient testPatient = new Patient(59, 2, 32.1, 100, 0, 157, 93.0, 37.5, 4.0, 4.859, 87);

        // Calculate and store the medians
        Dataset dataset = new Dataset(testPatientData, 6, 0); // Create an instance of the Dataset class
        dataset.calculateAndStoreMedians(testPatientData); // Call the method on the dataset object

        // Validate the test patient data
        Patient validatedPatient = dataset.validatePatientData(testPatient);

        // Assertions for each field
        assertEquals(59, validatedPatient.getAge());
        assertEquals(2, validatedPatient.getGender());
        assertEquals(32.1, validatedPatient.getBmi());
        assertEquals(100, validatedPatient.getBloodPressure());
        assertEquals(0, validatedPatient.getTotalSerumCholesterol());
        assertEquals(157, validatedPatient.getLdl());
        assertEquals(93.0, validatedPatient.getHdl());
        assertEquals(37.5, validatedPatient.getTch());
        assertEquals(4.0, validatedPatient.getLtg());
        assertEquals(4.859, validatedPatient.getGlu());
    }
}