import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RandomForest {
    DecisionTree[] DecisionTrees = new DecisionTree[MAX_TREES];
    public static final int MAX_TREES = 10;
    Dataset Data;
    MaxHeap maxHeap;
    List<Patient> outOfBagSample = new ArrayList<>();

    RandomForest(Dataset data){
        //DecisionTrees=new DecisionTree[MAX_TREES];
        Data=data;
        maxHeap = new MaxHeap(MAX_TREES);
    }

    void readFile(File f) {
        try {
            Data=Data.readFile(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void RandomForestAlg(){
        for(int i=0; i<Data.totalNumber; i++){
            DecisionTrees[i]=obtainTree();
        }
    }

    public DecisionTree obtainTree() {
        Random random = new Random();
        //bootstrapping sample
        List<Patient> patients = Data.getPatients();//new ArrayList<Patient>(Arrays.asList(Data.getPatients()));
        List<Patient> bootStrapPatients = createBootStrap(patients, random);

        List<Patient> outOfBagPatients = Data.getPatients();//new ArrayList<Patient>(Arrays.asList(Data.getPatients()));
        outOfBagPatients.removeAll(bootStrapPatients);
        outOfBagSample.addAll(outOfBagPatients);

        DecisionTree dt=new DecisionTree();
        LinkedList<Patient> PatientLinkedList = new LinkedList<>();
        for (Patient t : patients) {
            PatientLinkedList.add(t);
        }
        dt.buildDecisionTree(PatientLinkedList,0);
        return dt;
    }

    public List<Patient> createBootStrap(List<Patient> data, Random random){
        List<Patient> bootStrapPatients = new ArrayList<>();

        for(int i=0; i < data.size(); i++){
            int randInt = random.nextInt(data.size());
            bootStrapPatients.add(data.get(randInt));
        }

        return bootStrapPatients;
    }

    int[] predict(){
        int[] predictResult=new int[Data.getTotalNumber()];
        //DecisionTrees=//readTree();
        int num = 0;
        for(Patient p: Data.getPatients()){
            int[] treeResult = new int[10];
            int c=0;
            for(int i:treeResult){
                i=maxHeap.heap[i+1].predict(p);
                c++;
            }
            if(c>5){ //majority vote
                predictResult[num]=1;
            }else {
                predictResult[num] = 0;
            }num++;
        }
        return predictResult;
    }

    public DecisionTree[] getDecisionTrees() {
        return DecisionTrees;
    }
}
