import java.io.BufferedWriter;
import java.io.IOException;

public class TreeNode {
	TreeNode leftChild;
	TreeNode rightChild;
	BranchingCiteria Branch;
	
	TreeNode(BranchingCiteria b){
		Branch=b;
		leftChild=null;
		rightChild=null;
	}
	
	void print(BufferedWriter w) throws IOException {
		w.write(Branch.Name+"; "+Branch.Value+"->");
	}
}
