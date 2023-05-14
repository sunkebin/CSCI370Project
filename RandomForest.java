import java.io.File;
import java.io.IOException;
import java.util.*;

public class RandomForest {
    DecisionTree[] DecisionTrees;
    public static final int MAX_TREES = 10;
    Dataset Data;
    List<Patient> outOfBagSample;

    RandomForest(){
        DecisionTrees=new DecisionTree[MAX_TREES];
        Data=null;
    }

    void readFile(File f) throws IOException {
        Data=Data.readFile(f);
    }

    void RandomForestAlg(){
        for(int i=0; i<MAX_TREES; i++){
            DecisionTrees[i]=obtainTree();
        }
    }

    public DecisionTree obtainTree() {
        Random random = new Random();
        //bootstrapping sample
        List<Patient> patients = new ArrayList<Patient>(Arrays.asList(Data.getPatients()));
        List<Patient> bootStrapPatients = createBootStrap(patients, random);

        List<Patient> outOfBagPatients = new ArrayList<Patient>(Arrays.asList(Data.getPatients()));
        outOfBagPatients.removeAll(bootStrapPatients);
        outOfBagSample.addAll(outOfBagPatients);

        DecisionTree dt=new DecisionTree();
        LinkedList<Patient> PatientLinkedList = new LinkedList<>();
        for (Patient t : patients) {
            PatientLinkedList.add(t);
        }
        dt.buildDecisionTree(PatientLinkedList);
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
        DecisionTrees=readTree();
        int num = 0;
        for(Patient p: Data.getPatients()){
            int[] treeResult = new int[10];
            int c=0;
            for(int i=0;i<10;i++){
                treeResult[i]=DecisionTrees[i].predict(p);
                c+=treeResult[i];
            }
            if(c>5){
                predictResult[num]=1;
            }else {
                predictResult[num] = 0;
            }num++;
        }
        return predictResult;
    }

    private DecisionTree[] readTree() {

    }
}
