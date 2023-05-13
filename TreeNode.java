import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

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
}
