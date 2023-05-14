import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class treeNode {
	BranchingCriteria Branch;
	treeNode leftChild;
	treeNode rightChild;
	double score;

	treeNode(BranchingCriteria b, double s){
		Branch=b;
		score=s;
	}

	void print(BufferedWriter w) throws IOException {
		w.write(Branch.Name+"; "+Branch.Value+"->");
	}
	public treeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(treeNode leftChild) {
		this.leftChild = leftChild;
	}

	public treeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(treeNode rightChild) {
		this.rightChild = rightChild;
	}

	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}
	public List<treeNode> getChildNodes() {
		List<treeNode> childNodes = new ArrayList<>();
		if (leftChild != null) {
			childNodes.add(leftChild);
		}
		if (rightChild != null) {
			childNodes.add(rightChild);
		}
		return childNodes;
	}
}
