package src;

public class BranchingCriteria {
	public String Name;
	public Double Value;

	public BranchingCriteria(String n, double v){
		Name=n;
		Value=v;
	}

	public BranchingCriteria newBranchingCriteria(String name, int value) {
		return new BranchingCriteria(name, value);
	}

	public String getName() {
		return Name;
	}

	public Double getValue() {
		return Value;
	}
}
