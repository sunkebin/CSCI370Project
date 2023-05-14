import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTests {

    @Test
    public void unitTestRandomForestAlg() throws IOException {
        Dataset dataset = new Dataset();
        dataset.readFile(new File("unitTest.csv"));
        RandomForest rf=new RandomForest(dataset);
        rf.RandomForestAlg();
        List<TreeNode> treeNodes = new ArrayList<>();
        int score = 0;

        BranchingCriteria bcBMI = new BranchingCriteria("BMI", dataset.getPatients().get(0).getBmi());
        TreeNode treeNodeBMI = new TreeNode(bcBMI,0);

        BranchingCriteria bcS1 = new BranchingCriteria("S1", dataset.getPatients().get(0).getTotalSerumCholesterol());
        TreeNode treeNodeS1 = new TreeNode(bcS1,0);

        BranchingCriteria bcS3 = new BranchingCriteria("S3", dataset.getPatients().get(0).getHdl());
        TreeNode treeNodeS3 = new TreeNode(bcS3,0);

        treeNodes.add(treeNodeBMI);
        treeNodes.add(treeNodeS1);
        treeNodes.add(treeNodeS3);

        DecisionTree input1 = new DecisionTree(treeNodes,treeNodeBMI);

        treeNodes = new ArrayList<>();

        BranchingCriteria bcBP = new BranchingCriteria("BP", dataset.getPatients().get(1).getBloodPressure());
        TreeNode treeNodeBP = new TreeNode(bcBP,0);

        BranchingCriteria bcS2 = new BranchingCriteria("S2", dataset.getPatients().get(1).getLdl());
        TreeNode treeNodeS2 = new TreeNode(bcS2,0);


        bcS3 = new BranchingCriteria("S3", dataset.getPatients().get(1).getHdl());
        treeNodeS3 = new TreeNode(bcS3,0);

        BranchingCriteria bcS4 = new BranchingCriteria("S4", dataset.getPatients().get(1).getHdl());
        TreeNode treeNodeS4 = new TreeNode(bcS4,0);

        treeNodes.add(treeNodeBMI);
        treeNodes.add(treeNodeS1);
        treeNodes.add(treeNodeS3);
        treeNodes.add(treeNodeS4);

        DecisionTree input2 = new DecisionTree(treeNodes,treeNodeBP);

        treeNodes = new ArrayList<>();

        BranchingCriteria bcS6 = new BranchingCriteria("S6", dataset.getPatients().get(2).getBloodPressure());
        TreeNode treeNodeS6 = new TreeNode(bcBP,0);

        BranchingCriteria bcS5 = new BranchingCriteria("S5", dataset.getPatients().get(3).getDiseaseProgressionValue());
        TreeNode treeNodeS5 = new TreeNode(bcS5,0);

        bcBMI = new BranchingCriteria("BMI", dataset.getPatients().get(3).getBmi());
        treeNodeBMI = new TreeNode(bcBMI,0);

        treeNodes.add(treeNodeS6);
        treeNodes.add(treeNodeS5);
        treeNodes.add(treeNodeBMI);

        DecisionTree input3 = new DecisionTree(treeNodes,treeNodeS6);

        assertEquals(rf.getDecisionTrees()[0].readDecisionTree(),input1);
        assertEquals(rf.getDecisionTrees()[1].readDecisionTree(),input2);
        assertEquals(rf.getDecisionTrees()[2].readDecisionTree(),input3);
    }

    @Test
    public void unitTestDecisionTree() throws IOException {
        Dataset dataset = new Dataset();
        dataset.readFile(new File("unitTest.csv"));
        RandomForest rf=new RandomForest(dataset);
        rf.RandomForestAlg();
        DecisionTree newDecisionTree = rf.getDecisionTrees()[0];
        newDecisionTree.write();

        String filePath = "output.txt";
        File file = new File(filePath);

        assertTrue(file.exists() && !file.isDirectory());

    }

    @Test
    public void unitTestDataset() throws IOException {
        Dataset dataset = new Dataset();
        dataset.readFile(new File("unitTest.csv"));

        assertEquals(dataset.readFile(new File("unitTest.csv")),dataset);

        assertThrows(FileNotFoundException.class,() -> {dataset.readFile(new File("unitTest.txt"));});

    }


    @Test
    public void unitTestDatasetValidate() throws Exception {
        Dataset dataset = new Dataset();
        dataset.readFile(new File("unitTest.csv"));

        Patient firstPatient = dataset.patients.get(0);
        Dataset dataset2 = new Dataset();
        List<Patient> patientList = new ArrayList<>();
        patientList.add(firstPatient);
        dataset2.setPatients(patientList);


        Patient newPatientNULL =new Patient(59,2,32.1,0,0,157,0
                ,0,4.0,0,0);
        List<Patient> patientListNULL = new ArrayList<>();
        Dataset dataset3 = new Dataset();
        dataset3.setPatients(patientListNULL);

        assertTrue(dataset.validatePatientData());
        assertTrue(dataset2.validatePatientData());
        assertEquals(dataset.validatePatientData(newPatientNULL),firstPatient);

    }


    @Test
    public void unitTestPatient() throws IOException {

        Patient newPatient = new Patient(59,2,32.1,100,0,157,93.0
                ,37.5,4.0,4.859,87);
        assertEquals(new Patient(59,2,32.1,100,0,157,93.0
                ,37.5,4.0,4.859,87),newPatient);


        Patient newPatientNULL =new Patient(59,2,32.1,0,0,157,0
                ,0,4.0,0,0);

        assertEquals(new Patient(59,2,32.1,0,0,157,0
                ,0,4.0,0,0),newPatientNULL);
    }

}
