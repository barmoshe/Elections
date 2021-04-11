package id314022914_id206921777;

public class Result {

	private int numOfvotes;
	private String partyName;

	public boolean setPartyName(String partyName) {
		this.partyName = partyName;
		return true;
	}

	public int getNumOfvotes() {
		return numOfvotes;
	}

	public String getPartyName() {
		return partyName;
	}

	public Result(String partyName) {
		this.numOfvotes = 0;
		this.partyName = partyName;
	}

	public void addVote() {
		this.numOfvotes++;
	}

	@Override
	public String toString() {
		return ("Result  for " + this.partyName + "=" + this.numOfvotes + "\n");
	}

	public void showResult() {
		System.out.println("num of votes for " + this.partyName + " is : " + this.numOfvotes);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Result))
			return false;
		Result temp = (Result) obj;
		if (temp.partyName == this.partyName)
			return true;
		else
			return false;
	}
}
