import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DecisionTree {
    public static final int HEIGHT_LIMIT = 10;
    public static final int MAX_LEAVES = (int) Math.pow(2, 10);
    int impurity = 1;
    List<TreeNode> treeNodes;
    TreeNode root;

    public DecisionTree(List<TreeNode> treeNodes, TreeNode root){
        this.treeNodes = treeNodes;
        this.root = root;
    }

    public TreeNode buildDecisionTree(LinkedList<Patient> data, int currentHeight) {
        if (currentHeight >= HEIGHT_LIMIT || isLeafLimitReached(root) || impurity <= 0) {
            return currNode;
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
    }

    public BranchingCriteria learnBranchingCriteria(LinkedList<Patient> data) {
        String bestCriteriaName = null;
        double bestCriteriaValue = Double.NaN;

        // Calculate and compare metrics for each criterion
        double bestImpurity = Double.POSITIVE_INFINITY; // Initialize with a high value to find the minimum impurity

        // Evaluate each criterion and choose the one with the lowest impurity
        for (String criterion : getAvailableCriteria()) {
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

        // Create and return the branching criteria with the best split
        return new BranchingCriteria(bestCriteriaName, bestCriteriaValue);
    }

    private double calculateImpurity(LinkedList<Patient> data, String criterion, double median) {
    }

    public List<String> getAvailableCriteria() {
        List<String> criteria = new ArrayList<>();

        criteria.add("age");
        criteria.add("gender");
        criteria.add("bmi");
        criteria.add("bloodPressure");
        criteria.add("totalSerumCholesterol");
        criteria.add("ldl");
        criteria.add("hdl");
        criteria.add("tch");
        criteria.add("ltg");
        criteria.add("glu");

        return criteria;
    }

    private boolean isLeafLimitReached(TreeNode node) {
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

    public void predict(Patient patient){

    }

    public void addNode(TreeNode node){
        treeNodes.add(node);
    }

    public void write(){

    }

    public void readDecisionTree(){

    }

    public void Report(){

    }

    public void Branching(TreeNode node, BranchingCriteria criteria, List<TreeNode> children){

    }
}
