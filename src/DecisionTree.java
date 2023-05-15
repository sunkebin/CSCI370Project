package src;

import java.util.*;

public class DecisionTree {
    public static final int HEIGHT_LIMIT = 10;
    public static final int MAX_LEAVES = (int) Math.pow(2, 10);
    public MaxHeap maxHeap = new MaxHeap(MAX_LEAVES);
    public int impurity = 1;
    public List<treeNode> treeNodes;
    public treeNode root;
    public List<String> criteria = new ArrayList<>();
    public double Accuracy;
    public List<String> availableCriteria;


    public DecisionTree(List<treeNode> treeNodes, treeNode root){
        this.treeNodes = treeNodes;
        this.root = root;
        Accuracy=0.0;
        criteria.addAll(Arrays.asList("age", "gender", "bmi", "bloodPressure", "totalSerumCholesterol","ldl", "hdl", "tch", "ltg", "glu"));
        availableCriteria = new ArrayList<>(criteria);
    }

    public DecisionTree(){
        this.treeNodes = new LinkedList<>();
        this.root = null;
        Accuracy=0.0;
        criteria.addAll(Arrays.asList("age", "gender", "bmi", "bloodPressure", "totalSerumCholesterol","ldl", "hdl", "tch", "ltg", "glu"));
        availableCriteria = new ArrayList<>(criteria);
    }

    public DecisionTree(LinkedList<Patient> patientData){
        this.treeNodes = new LinkedList<>();
        this.root = new treeNode(patientData);
        this.treeNodes.add(this.root);
        Accuracy=0.0;
        criteria.addAll(Arrays.asList("age", "gender", "bmi", "bloodPressure", "totalSerumCholesterol","ldl", "hdl", "tch", "ltg", "glu"));
        availableCriteria = new ArrayList<>(criteria);
    }

    public void buildDecisionTree(LinkedList<Patient> data) {
        int counter=0;
        while (counter <11 || impurity <= 0) {
            BranchingCriteria branchingCriteria = learnBranchingCriteria(data);
            treeNode node = splitData(data, branchingCriteria);
            maxHeap.insert(node);
            counter++;
        }
    }


    public treeNode splitData(LinkedList<Patient> data, BranchingCriteria branchingCriteria) {
        List<LinkedList<Patient>> subsets = new ArrayList<>();
        LinkedList<Patient> leftSubset = new LinkedList<>();
        LinkedList<Patient> rightSubset = new LinkedList<>();
        treeNode node = new treeNode(branchingCriteria,0);
        int score=0;
        for (Patient patient : data) {
            double criterionValue = patient.getCriterionValue(branchingCriteria.getName());
            if (criterionValue <= branchingCriteria.getValue()) {
                leftSubset.add(patient);
                if(patient.getDiseaseProgressionValue()>150){
                    score++;
                }else{
                    score--;
                }
            } else {
                rightSubset.add(patient);
                if(patient.getDiseaseProgressionValue()>150){
                    score++;
                }else{
                    score--;
                }
            }
        }
        node.Branch=branchingCriteria;
        node.score=score;
        subsets.add(leftSubset);
        node.leftPatients=leftSubset;
        subsets.add(rightSubset);
        node.rightPatients=rightSubset;
        return node;
    }

    public BranchingCriteria learnBranchingCriteria(LinkedList<Patient> data) {
        String bestCriteriaName="";
        double bestCriteriaValue = Double.NaN;
        // Calculate and compare metrics for each criterion
        double bestImpurity = Double.POSITIVE_INFINITY; // Initialize with a high value to find the minimum impurity

        // Evaluate each criterion and choose the one with the lowest impurity
        for (String criterion : availableCriteria) {
            List<Double> criterionValues = new ArrayList<>();

            // Extract the values of the current criterion from the patient data
            for (Patient patient : data) {
                double value = patient.getCriterionValue(criterion);
                criterionValues.add(value);
            }
            // Sort the criterion values and calculate the median
            Collections.sort(criterionValues);
            double median = criterionValues.get(criterionValues.size() / 2);

            // Calculate the impurity of the split using the criterion
            double impurity = calculateImpurity(data, criterion, median);

            // Check if the current criterion provides a better split
            if (impurity < bestImpurity) {
                bestImpurity = impurity;
                bestCriteriaName = criterion;
                bestCriteriaValue = median;
            }
        }
        availableCriteria.remove(bestCriteriaName);
        // Create and return the branching criteria with the best split
        return new BranchingCriteria(bestCriteriaName, bestCriteriaValue);
    }

    public double calculateImpurity(LinkedList<Patient> data, String criterion, double median) {
        int totalCount = data.size();
        int leftCount = 0;
        int rightCount = 0;

        // Count the number of patients on the left and right sides of the split
        for (Patient patient : data) {
            double criterionValue = patient.getCriterionValue(criterion);
            if (criterionValue <= median) {
                leftCount++;
            } else {
                rightCount++;
            }
        }

        // Calculate the probabilities of each side
        double leftProbability = (double) leftCount / totalCount;
        double rightProbability = (double) rightCount / totalCount;

        // Calculate the Gini index
        double giniLeft = 1 - Math.pow(leftProbability, 2) - Math.pow(1 - leftProbability, 2);
        double giniRight = 1 - Math.pow(rightProbability, 2) - Math.pow(1 - rightProbability, 2);

        // Calculate the weighted average of the Gini indices
        double impurity = (leftCount * giniLeft + rightCount * giniRight) / totalCount;

        return impurity;
    }

    public List<String> getAvailableCriteria() {
        return criteria;
    }

    public boolean isLeafLimitReached(treeNode node) {
        if (node.isLeaf()) {
            return true;
        }

        for (treeNode child : node.getChildNodes()) {
            if (isLeafLimitReached(child)) {
                return true;
            }
        }
        return false;
    }

    public int predict(Patient patient) {
        int i=1;
        treeNode curr=maxHeap.heap[i];
        while(i<=maxHeap.size){
            curr=maxHeap.heap[i];
            double v=patient.getCriterionValue(curr.Branch.getName());
            double bv=curr.Branch.getValue();
            if(v<bv) {
                i=i*2;
            }
            else i=i*2+1;
        }
        return curr.score;
    }

    public void addNode(treeNode node){
        treeNodes.add(node);
    }

    public double getImpurity() {
        return impurity;
    }
}
