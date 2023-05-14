import java.io.*;
import java.util.*;

public class DecisionTree {
    public static final int HEIGHT_LIMIT = 10;
    public static final int MAX_LEAVES = (int) Math.pow(2, 10);
    //MaxHeap maxHeap = new MaxHeap(MAX_LEAVES);
    int impurity = 1;
    List<TreeNode> treeNodes;
    TreeNode root;
    List<String> criteria = new ArrayList<>();

    public DecisionTree(List<TreeNode> treeNodes, TreeNode root){
        this.treeNodes = treeNodes;
        this.root = root;
        criteria.addAll(Arrays.asList("age", "gender", "bmi", "bloodPressure", "totalSerumCholesterol","ldl", "hdl", "tch", "ltg", "glu"));
    }

    public DecisionTree(){
        this.treeNodes = null;
        this.root = null;
        criteria.addAll(Arrays.asList("age", "gender", "bmi", "bloodPressure", "totalSerumCholesterol","ldl", "hdl", "tch", "ltg", "glu"));
    }

    public TreeNode buildDecisionTree(LinkedList<Patient> data, int currentHeight) {
        if (currentHeight >= HEIGHT_LIMIT || isLeafLimitReached(root) || impurity <= 0) {
            return root;
        } else {
            BranchingCriteria branchingCriteria = learnBranchingCriteria(data);

            List<LinkedList<Patient>> subsets = splitData(data, branchingCriteria);

            List<TreeNode> subTrees = new LinkedList<>();
            for (LinkedList<Patient> subset : subsets) {
                subTrees.add(buildDecisionTree(subset, currentHeight + 1));
            }
            return root;
        }
    }

    public List<LinkedList<Patient>> splitData(LinkedList<Patient> data, BranchingCriteria branchingCriteria) {
        List<LinkedList<Patient>> subsets = new ArrayList<>();
        LinkedList<Patient> leftSubset = new LinkedList<>();
        LinkedList<Patient> rightSubset = new LinkedList<>();

        for (Patient patient : data) {
            double criterionValue = patient.getCriterionValue(branchingCriteria.getName());
            if (criterionValue <= branchingCriteria.getValue()) {
                leftSubset.add(patient);
            } else {
                rightSubset.add(patient);
            }
        }

        subsets.add(leftSubset);
        subsets.add(rightSubset);
        return subsets;
    }

    public BranchingCriteria learnBranchingCriteria(LinkedList<Patient> data) {
        String bestCriteriaName = null;
        double bestCriteriaValue = Double.NaN;
        // Calculate and compare metrics for each criterion
        double bestImpurity = Double.POSITIVE_INFINITY; // Initialize with a high value to find the minimum impurity

        // Evaluate each criterion and choose the one with the lowest impurity
        List<String> availableCriteria = getAvailableCriteria();
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

    public boolean isLeafLimitReached(TreeNode node) {
        if (node.isLeaf()) {
            return true;
        }

        for (TreeNode child : node.getChildNodes()) {
            if (isLeafLimitReached(child)) {
                return true;
            }
        }
        return false;
    }

    public int predict(Patient patient) {
        TreeNode curr = root;

        while (!curr.isLeaf()) {
            String criterionName = curr.Branch.getName();
            double criterionValue = curr.Branch.getValue();

            if (patient.getCriterionValue(criterionName) <= criterionValue) {
                curr = curr.getLeftChild();
            } else {
                curr = curr.getRightChild();
            }
        }

        // Count the number of left and right branches
        int leftCount = countBranches(curr, true);
        int rightCount = countBranches(curr, false);

        // Make prediction based on majority class
        if (leftCount <= rightCount) {
            return 0;  // Predict "no diabetes"
        } else {
            return 1;  // Predict "diabetes"
        }
    }

    private int countBranches(TreeNode node, boolean isLeft) {
        if (node.isLeaf()) {
            return isLeft ? 1 : 0;
        }

        int count = 0;
        if (isLeft) {
            count += countBranches(node.getLeftChild(), true);
        } else {
            count += countBranches(node.getRightChild(), false);
        }
        return count;
    }


    public void addNode(TreeNode node){
        treeNodes.add(node);
    }

    public void write(){

        try {
            // Creates a FileWriter
            FileWriter writer = new FileWriter("output.txt");

            // Wraps the FileWriter in a BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Writes the string to the file
            for (TreeNode node: treeNodes) {
                bufferedWriter.write(predict(node));
            }

            // Closes the writer
            bufferedWriter.close();
        }
        catch(IOException e) {
            // If an I/O error occurs
            e.printStackTrace();
        }

    }

    public List<TreeNode> readDecisionTree(){
        return treeNodes;
        //randomForest.getDecisionTrees();
    }


    public void Report(){

    }

    public void Branching(TreeNode node, BranchingCriteria criteria, List<TreeNode> children){

    }

    public double getImpurity() {
        return impurity;
    }

    public void newDecisionTrees() {

    }
}
