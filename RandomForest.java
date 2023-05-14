import java.io.*;
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
        }OOBValidation();
        WriteTree();
    }

    private void WriteTree() {
        try {
            File file = new File("log.txt");
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(MAX_TREES+"\n");
                for(int i=0; i<MAX_TREES;i++){
                    for(int j=1; j<DecisionTrees[i].maxHeap.size;j++){
                        DecisionTrees[i].maxHeap.heap[j].print(writer);
                    }writer.write("\n");
                }writer.close();
            } else {
                File f=new File("log.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(MAX_TREES+"\n");
                for(int i=0; i<MAX_TREES;i++){
                    for(int j=1; j<DecisionTrees[i].maxHeap.size;j++){
                        DecisionTrees[i].maxHeap.heap[j].print(writer);
                    }writer.write("\n");
                }writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public DecisionTree obtainTree() {
        Random random = new Random();
        //bootstrapping sample
        List<Patient> patients = Data.getPatients();
        List<Patient> bootStrapPatients = createBootStrap(patients, random);

        List<Patient> outOfBagPatients = Data.getPatients();
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

    int[] predict(File f) throws IOException {
        int[] predictResult=new int[Data.getTotalNumber()];
        DecisionTrees=readTree(f);
        int num = 0;
        for(Patient p: Data.getPatients()){
            int[] treeResult = new int[10];
            int c=0;
            for(int i=0;i<10;i++){
                treeResult[i]=DecisionTrees[i].predict(p);
                c+=treeResult[i];
            }
            if(c>0){
                predictResult[num]=1;
            }else {
                predictResult[num] = 0;
            }num++;
        }
        return predictResult;
    }

    void OOBValidation(){
        int[] actual=new int[outOfBagSample.size()];
        for(int i=0; i<outOfBagSample.size();i++){
            if(outOfBagSample.get(i).getDiseaseProgressionValue()>150){
                actual[i]=1;
            }else actual[i]=0;
        }
        int[][] predict=new int[MAX_TREES][outOfBagSample.size()];
        for(int i=0; i<MAX_TREES;i++){
            for(int j=0; j<outOfBagSample.size();j++){
                predict[i][j]=DecisionTrees[i].predict(outOfBagSample.get(j));
                if(predict[i][j]==actual[i]) {
                    DecisionTrees[i].Accuracy += 1 / outOfBagSample.size();
                }
            }
        }
    }

    private DecisionTree[] readTree(File f) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        int num=Integer.valueOf(reader.readLine());
        DecisionTree[] dts = new DecisionTree[num];
        String s;
        int count=0;
        while((s=reader.readLine())!=null||count<num){
            String[] TreeNodes=s.split(",");
            String[][] Tree = new String[TreeNodes.length][3];
            for(int i=0;i<TreeNodes.length;i++){
                Tree[i]=TreeNodes[i].split(";");
                String BN=Tree[i][0];
                double BV=Double.valueOf(Tree[i][1]);
                int SCORE=Integer.valueOf(Tree[i][2]);
                treeNode tn=new treeNode(new BranchingCriteria(BN,BV),SCORE);
                dts[count].maxHeap.heap[i+1]=tn;
            }count++;
        }return dts;
    }
}
