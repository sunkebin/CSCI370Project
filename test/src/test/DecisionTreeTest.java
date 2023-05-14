package src.test;

import org.junit.jupiter.api.Test;
import src.Patient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecisionTreeTest {

    Patient newPatient1;
    Patient newPatient2;
    Patient newPatient3;
    Patient newPatient4;
    Patient newPatient5;
    Patient newPatient6;
    List<Patient> testPatientData;

    public DecisionTreeTest() {
        newPatient1 = new Patient(59, 2, 32.1, 100, 0, 157, 93.0, 37.5, 4.0, 4.859, 87);
        newPatient2 = new Patient(48, 1, 21.6, 87, 193, 103.2, 70.0, 3.00, 3.891, 69, 75);
        newPatient3 = new Patient(72, 2, 30.5, 93.0, 156, 93.6, 41.0, 4.00, 4.672, 85, 141);
        newPatient4 = new Patient(24, 1, 25.3, 84, 198, 131.4, 40, 5, 4.890, 89, 206);
        newPatient5 = new Patient(50, 1, 23, 101, 192, 125.4, 52, 4, 4.920, 80, 135);
        newPatient6 = new Patient(23, 1, 22.6, 89, 139, 64.8, 61, 2, 4.189, 68, 97);

        testPatientData = new ArrayList<>();
        testPatientData.add(newPatient1);
    }

    @Test
    void buildDecisionTree() {
    }

    @Test
    void splitData() {
    }

    @Test
    void learnBranchingCriteria() {
    }

    @Test
    void calculateImpurity() {
    }

    @Test
    void predict() {
    }
}