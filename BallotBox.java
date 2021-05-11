
package id314022914_id206921777;

import java.util.ArrayList;

public class BallotBox<T extends Citizen> {
	private BallotType ballotType;
	private int serialNumber;
	private static int serialCounter = 1000;
	private String address;
	private SetForElections<Citizen> citizenInBallotBox;
	private double votePresentage;
	private ArrayList<Result> resultsForThisBallotBox;
	private int resultCount;
	private int citizensCount;

	public BallotBox(BallotBox b) {
		this.serialNumber = b.getSerialnumber();
		this.address = b.address;
		this.citizenInBallotBox = b.citizenInBallotBox;
		this.resultsForThisBallotBox = b.resultsForThisBallotBox;
		this.votePresentage = 0;
		this.resultCount = 0;
		this.citizensCount = 0;
	}

	public BallotBox(String address, BallotType ballotType) {
		this.serialNumber = serialCounter++;
		this.address = address;
		this.citizenInBallotBox = new SetForElections<Citizen>();
		this.votePresentage = 0;
		this.resultsForThisBallotBox = new ArrayList<Result>();
		this.resultCount = 0;
		this.citizensCount = 0;
		this.ballotType = ballotType;
	}

	public BallotBox() {
		this.serialNumber = serialCounter++;
		this.address = null;
	}

	public BallotBox(BallotBox b, BallotType bType) {

	}

	public int cheakIfCitizenExist(Citizen c) {
		return this.citizenInBallotBox.exist(c);

	}

	public BallotType getType() {
		return this.ballotType;
	}

	public void addCitizen(Citizen c) throws Exception {
		int index = cheakIfCitizenExist(c);
		if (index != -1) {
			citizenInBallotBox.replace(index, c);
		} else {
			citizenInBallotBox.add(c);
			this.citizensCount = this.citizensCount + 1;
		}

	}

	public boolean cheakIfresultExist(Result r) {
		for (int i = 0; i < this.resultCount; i++) {
			if (this.resultsForThisBallotBox.get(i).equals(r)) {
				System.out.println("exist");// *
				return true;
			}
		}
		return false;
	}

	public void addToResult(String name) {
		Result r = new Result(name);
		if (cheakIfresultExist(r))
			return;
		resultsForThisBallotBox.add(r);
		this.resultCount = this.resultCount + 1;
	}

	public double voteResultsAndPercentage() {// returns percentage of votes and calculate votes for each party
		int nonVotersAmount = 0;
		for (int i = 0; i < this.citizensCount; i++) {
			Citizen current = citizenInBallotBox.get(i);
			if (current.getPartyChosen() == null)
				nonVotersAmount++;
			else
				for (int j = 0; j < resultCount; j++) {
					if (current.getPartyChosen().equals(resultsForThisBallotBox.get(j).getPartyName())) {
						resultsForThisBallotBox.get(j).addVote();
						
					}
				}

		}
		if (this.citizensCount > 0)
			return ((double) (this.citizensCount - nonVotersAmount) / this.citizensCount) * 100;
		else
			return 0;
	}

	public int getResultForParty(String name) {
		for (int i = 0; i < this.resultCount; i++) {
			if (name == resultsForThisBallotBox.get(i).getPartyName())
				return resultsForThisBallotBox.get(i).getNumOfvotes();
		}
		return 0;
	}

	public void showResult() {
		System.out.println("________#" + this.serialNumber + "#________");
		for (int i = 0; i < resultCount; i++) {
			resultsForThisBallotBox.get(i).showResult();
		}
		System.out.println("vote precentage: " + voteResultsAndPercentage() + "\n");
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(this.getClass().getSimpleName() + " #" + this.serialNumber + " info:" + "\n"
				+ "location: " + this.address + "\n ballot type : " + this.ballotType.toString() + "\n\n");
		str.append("the citizens in the ballotbox: \n");
		for (int i = 0; i < this.citizensCount; i++) {
			str.append((i + 1) + ") " + this.citizenInBallotBox.get(i).getName() + "\n");
		}

		str.append("\nvote precentage: " + this.votePresentage + "\n" + "\nresults in this ballotbox: \n");
		for (int i = 0; i < this.resultCount; i++) {
			str.append(resultsForThisBallotBox.get(i).toString());// *
		}
		return str.toString();

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BallotBox))
			return false;
		BallotBox temp = (BallotBox) obj;
		if (temp.serialNumber == this.serialNumber)
			return true;
		else
			return false;
	}

	public int getSerialnumber() {
		return this.serialNumber;
	}

	public void replace(Citizen tempC, Candidate c) throws Exception {
		String currentId = tempC.getId();
		this.citizenInBallotBox.replace(this.citizenInBallotBox.existById(currentId), c);
	}

}