import java.io.BufferedWriter;
import java.io.IOException;

public class TreeNode {
	BranchingCiteria Branch;
	linkedList leftPatients;
	linkedList rightPatients;
	double score;
 	
	TreeNode(BranchingCiteria b, double s){
		Branch=b;
		leftPatients=new linkedList();
		rightPatients=new linkedList();
		score=s;
	}
	
	void print(BufferedWriter w) throws IOException {
		w.write(Branch.Name+"; "+Branch.Value+"->");
	}
}
