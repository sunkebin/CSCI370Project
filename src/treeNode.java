package src;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class treeNode {
	public BranchingCriteria Branch;
	public List<Patient> allPatients;
	public LinkedList<Patient> leftPatients;
	public LinkedList<Patient> rightPatients;
	int score;


	public treeNode(BranchingCriteria b, int s){
		Branch=b;
		leftPatients= new LinkedList<Patient>();
		rightPatients= new LinkedList<Patient>();
		score=s;
	}
	public treeNode(){
		Branch=new BranchingCriteria("dummy",0.0);
		leftPatients= new LinkedList<Patient>();
		rightPatients= new LinkedList<Patient>();
		score=0;
	}

	public treeNode(LinkedList<Patient> allPatients){
		Branch=new BranchingCriteria("dummy",0.0);
		this.allPatients = allPatients;
		leftPatients= new LinkedList<Patient>();
		rightPatients= new LinkedList<Patient>();
		score=0;
	}

	public void print(BufferedWriter w) throws IOException {
		w.write(Branch.Name+";"+Branch.Value+";"+score+",");
	}
	public LinkedList<Patient> getLeftPatients() {
		return leftPatients;
	}

	public LinkedList<Patient> getRightPatients() {
		return rightPatients;
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
