import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class treeNode {
	BranchingCriteria Branch;
	LinkedList<Patient> AllPatients;
	LinkedList<Patient> leftPatients;
	LinkedList<Patient> rightPatients;
	double score;
 	
	treeNode(BranchingCriteria b, double s){
		Branch=b;
		AllPatients=new LinkedList<Patient>();
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

	public List<treeNode> getChildNodes() {
		List<treeNode> childNodes = new ArrayList<>();
		treeNode leftNode = new treeNode(null, 0);
		leftNode.leftPatients = leftPatients;
		treeNode rightNode = new treeNode(null, 0);
		rightNode.rightPatients = rightPatients;
		childNodes.add(leftNode);
		childNodes.add(rightNode);
		return childNodes;
	}
}
