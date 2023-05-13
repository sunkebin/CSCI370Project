import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
	BranchingCriteria Branch;
	LinkedList<Patient> leftPatients;
	LinkedList<Patient> rightPatients;
	double score;
 	
	TreeNode(BranchingCriteria b, double s){
		Branch=b;
		leftPatients= new LinkedList<Patient>();
		rightPatients= new LinkedList<Patient>();
		score=s;
	}
	
	void print(BufferedWriter w) throws IOException {
		w.write(Branch.Name+"; "+Branch.Value+"->");
	}

	public boolean isLeaf() {
		return leftPatients.isEmpty() && rightPatients.isEmpty();
	}

	public List<TreeNode> getChildNodes() {
		List<TreeNode> childNodes = new ArrayList<>();
		TreeNode leftNode = new TreeNode(null, 0);
		leftNode.leftPatients = leftPatients;
		TreeNode rightNode = new TreeNode(null, 0);
		rightNode.rightPatients = rightPatients;
		childNodes.add(leftNode);
		childNodes.add(rightNode);
		return childNodes;
	}
}
