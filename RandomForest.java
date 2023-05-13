import java.io.File;
import java.io.IOException;
import java.util.*;

public class RandomForest {
    DecisionTree[] DecisionTrees;
    Dataset Data;

    RandomForest(){
        DecisionTrees=new DecisionTree[10];
        Data=null;
    }

    void readFile(File f) throws IOException {
        Data=Data.readFile(f);
    }

    void RandomForestAlg(){
        for(int i=0; i<10; i++){
            DecisionTrees[i]=obtainTree();
        }
    }

    private DecisionTree obtainTree() {
        Random random = new Random();
        List<Patient> patients =new ArrayList<Patient>(Arrays.asList(Data.getPatients()));
        List<Patient> test =new ArrayList<Patient>();
        int randomInt = random.nextInt(patients.size()/2);
        for(int i=0; i<randomInt;i++){
            int r=random.nextInt(patients.size());
            test.add(patients.get(r));
            patients.remove(r);
        }//up to now, patients is sub dataset for obtain a tree; test is sub dataset for testing

    }
}
