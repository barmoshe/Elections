package Elections;

public class Candidate extends Citizen {

	protected Party partyBelong;
	protected int numOfVotes;

	public Candidate(Citizen c) throws Exception {
		super(c);
		this.numOfVotes = 0;

	}

	public Candidate(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth, Party party)
			throws Exception {
		super(name, id, isQuarentied, hasMask, yearOfBirth);
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
}