import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TreeNode {
	BranchingCriteria Branch;
	LinkedList<patient> leftPatients;
	LinkedList<patient> rightPatients;
	double score;
 	
	TreeNode(BranchingCriteria b, double s){
		Branch=b;
		leftPatients= new LinkedList<patient>();
		rightPatients= new LinkedList<patient>();
		score=s;
	}
	
	void print(BufferedWriter w) throws IOException {
		w.write(Branch.Name+"; "+Branch.Value+"->");
	}
}
