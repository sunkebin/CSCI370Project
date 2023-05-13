import java.io.File;
import java.io.IOException;
import java.util.*;

public class RandomForest {
    DecisionTree[] DecisionTrees;
    Dataset Data;
    MaxHeap maxHeap;

    RandomForest(){
        DecisionTrees=new DecisionTree[10];
        Data=null;
        maxHeap=new MaxHeap(11);
    }

    void readFile(File f) throws IOException {
        Data=Data.readFile(f);
    }

    void RandomForestAlg(){
        for(int i=0; i<10; i++){
            DecisionTrees[i]=obtainTree();
            maxHeap.insert(DecisionTrees[i]);
        }
    }

    private DecisionTree obtainTree() {
        Random random = new Random();
        List<Patient> patients =new ArrayList<Patient>(Arrays.asList(Data.getPatients()));
        List<Patient> test =new ArrayList<Patient>();
        int randomInt = random.nextInt(patients.size()/3);//test should be small
        for(int i=0; i<randomInt;i++){
            int r=random.nextInt(patients.size());
            test.add(patients.get(r));
            patients.remove(r);
        }//up to now, patients is sub dataset for obtain a tree; test is sub dataset for testing
        DecisionTree dt=new DecisionTree();
        LinkedList<Patient> PLL = new LinkedList<>();
        for (Patient t : patients) {
            PLL.add(t);
        }
        dt.buildDecisionTree(PLL,0);
        return dt;
    }

    int[] predict(){
        int[] predictResult=new int[Data.getTotalNumber()];
        DecisionTrees=readTree();
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

    private DecisionTree[] readTree() {

    }
}
