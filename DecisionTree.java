import java.util.List;

public class DecisionTree {
    List<TreeNode> treeNodes;
    TreeNode root;

    public DecisionTree(List<TreeNode> treeNodes, TreeNode root){
        this.treeNodes = treeNodes;
        this.root = root;
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
