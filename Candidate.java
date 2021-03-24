package Elections;

public class Candidate extends Citizen {

	protected Party partyBelong;
	protected int numOfVotes;

	public Candidate(Citizen c) {
		super(c);
		this.numOfVotes = 0;

	}

	public Candidate(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth, Party party) {
		super(name, id, isQuarentied, hasMask, yearOfBirth);
		this.partyBelong = party;
		this.numOfVotes = 0;

	}

	public void setPartyBelong(Party partyBelong) {
		this.partyBelong = partyBelong;
	}

	public void setNumOfVotes(int numberOfVotesForCand) {
		this.numOfVotes = numberOfVotesForCand;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + super.toString() + "party :" + this.partyBelong.getName()
				+ " num of votes PRIMERIZ : " + this.numOfVotes;
	}
}