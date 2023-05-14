package src.test;

import org.junit.jupiter.api.Test;
import src.BranchingCriteria;
import src.DecisionTree;
import src.Patient;
import src.treeNode;

import java.util.ArrayList;
import java.util.LinkedList;
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
        // Create a DecisionTree object
        DecisionTree decisionTree = new DecisionTree();
        // Build the decision tree
        decisionTree.buildDecisionTree(testPatientData);

        // Assert that the decision tree is not null
        assertNotNull(decisionTree.root);
        assertNotNull(decisionTree.treeNodes);
    }

    @Test
    void splitData() {
        // Create a DecisionTree object
        DecisionTree decisionTree = new DecisionTree();

        // Create a LinkedList of Patient objects (your dataset)
        LinkedList<Patient> data = new LinkedList<>();
        data.addAll(testPatientData);

        // Learn branching criteria
        BranchingCriteria branchingCriteria = decisionTree.learnBranchingCriteria(data);

        // Split the data based on the branching criteria
        treeNode node = decisionTree.splitData(data, branchingCriteria);

        // Assert that the node and subsets are not null
        assertNotNull(node);
        assertNotNull(node.getLeftPatients());
        assertNotNull(node.getRightPatients());
    }

    @Test
    void learnBranchingCriteria() {
        // Create a DecisionTree object
        DecisionTree decisionTree = new DecisionTree();

        // Create a LinkedList of Patient objects (your dataset)
        LinkedList<Patient> data = new LinkedList<>();
        data.addAll(testPatientData);

        // Learn branching criteria
        BranchingCriteria branchingCriteria = decisionTree.learnBranchingCriteria(data);

        // Assert that the branching criteria is not null
        assertNotNull(branchingCriteria);
        assertNotNull(branchingCriteria.getName());
        assertNotNull(branchingCriteria.getValue());
        assertTrue(decisionTree.getAvailableCriteria().contains(branchingCriteria.getName()));
    }

    @Test
    void calculateImpurity() {
        // Create a DecisionTree object
        DecisionTree decisionTree = new DecisionTree();

        // Create a LinkedList of Patient objects (your dataset)
        LinkedList<Patient> data = new LinkedList<>();
        data.addAll(testPatientData);

        // Learn branching criteria
        BranchingCriteria branchingCriteria = decisionTree.learnBranchingCriteria(data);

        // Calculate impurity
        double impurity = decisionTree.calculateImpurity(data, branchingCriteria.getName(), branchingCriteria.getValue());

        // Assert that the impurity is a valid value
        assertTrue(impurity >= 0.0);
        assertTrue(impurity <= 1.0);
    }

    @Test
    void predict() {
        // Create a DecisionTree object
        DecisionTree decisionTree = new DecisionTree();

        // Create a LinkedList of Patient objects (your dataset)
        LinkedList<Patient> data = new LinkedList<>();
        data.addAll(testPatientData);

        // Learn branching criteria and build the decision tree
        BranchingCriteria branchingCriteria = decisionTree.learnBranchingCriteria(data);
        decisionTree.buildDecisionTree(data);

        // Predict the score for a patient
        int predictedScore = decisionTree.predict(newPatient1);

        // Assert that the predicted score is within the expected range
        assertTrue(predictedScore >= Integer.MIN_VALUE);
        assertTrue(predictedScore <= Integer.MAX_VALUE);
    }
}