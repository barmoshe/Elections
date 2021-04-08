package id314022914_id206921777;

public class Result {

	protected int numOfvotes;
	private String idOrName;

	public boolean setIdOrName(String idOrName) {
		this.idOrName = idOrName;
		return true;
	}

	public int getNumOfvotes() {
		return numOfvotes;
	}

	public String getIdOrName() {
		return idOrName;
	}

	public Result(String idOrName) {
		this.numOfvotes = 0;
		this.idOrName = idOrName;
	}

	public void addVote() {
		this.numOfvotes++;
	}

	@Override
	public String toString() {
		return ("Result  for " + this.idOrName + "=" + this.numOfvotes + "\n");
	}

	public void showResult() {
		System.out.println("num of votes for " + this.idOrName + " is : " + this.numOfvotes);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Result))
			return false;
		Result temp = (Result) obj;
		if (temp.idOrName == this.idOrName)
			return true;
		else
			return false;
	}
}
