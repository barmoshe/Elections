package id314022914_id206921777;

public class Candidate extends Citizen implements Comparable<Candidate> {

	private Party partyBelong;
	private int numOfVotes;

	public Candidate(Citizen c) throws Exception {
		super(c);
		this.numOfVotes = 0;

	}

	public Candidate(String name, String id, int yearOfBirth, Party party) throws Exception {
		super(name, id, yearOfBirth);
		this.partyBelong = party;
		this.numOfVotes = 0;

	}

	public boolean setPartyBelong(Party partyBelong) {
		this.partyBelong = partyBelong;
		return true;
	}

	public boolean setNumOfVotes(int numberOfVotesForCand) {
		this.numOfVotes = numberOfVotesForCand;
		return true;

	}

	@Override
	public String toString() {
		return super.toString() + "\nparty: " + this.partyBelong.getName() + "\nnum of votes PRIMERIZ: "
				+ this.numOfVotes + "\n";
	}

	public Party getPartyBelong() {
		return this.partyBelong;
	}

	public int getNumOfVotes() {
		return this.numOfVotes;
	}

	@Override
	public int compareTo(Candidate o) {
		if (this.numOfVotes > o.numOfVotes)
			return -1;
		else if (this.numOfVotes > o.numOfVotes)
			return 1;
		else
			return 0;

	}
}