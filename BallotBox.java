
package Elections;

import java.util.Arrays;

public class BallotBox {

	private int serialNumber;
	private static int serialCounter = 1000;
	private String address;
	private Citizen[] citizenInBallotBox;
	private double votePresentage;
	private Result[] resultsForThisBallotBox;
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

	public BallotBox(String address) {
		this.serialNumber = serialCounter++;
		this.address = address;
		this.citizenInBallotBox = new Citizen[5];
		this.votePresentage = 0;
		this.resultsForThisBallotBox = new Result[5];
		this.resultCount = 0;
		this.citizensCount = 0;
	}

	public BallotBox() {
		this.serialNumber = serialCounter++;
		this.address = null;
	}

	public void copyAndMultiplyCitizens() {
		if (citizenInBallotBox[citizenInBallotBox.length - 1] != null) {
			Citizen[] temp = new Citizen[this.citizenInBallotBox.length * 2];
			for (int i = 0; i < this.citizenInBallotBox.length; i++) {
				temp[i] = this.citizenInBallotBox[i];
			}
			this.citizenInBallotBox = temp;
		}
	}

	public int cheakIfCitizenExist(Citizen c) {
		for (int i = 0; i < this.citizensCount; i++) {
			if (this.citizenInBallotBox[i].equals(c)) {
				return i;
			}
		}
		return -1;
	}

	public void addCitizen(Citizen c) {
		copyAndMultiplyCitizens();
		if (cheakIfCitizenExist(c) != -1) {
			citizenInBallotBox[cheakIfCitizenExist(c)] = c;
		} else {
			citizenInBallotBox[citizensCount] = c;
			this.citizensCount = this.citizensCount + 1;
		}

	}

	public void copyAndMultiplyResult() {
		if (resultsForThisBallotBox[resultsForThisBallotBox.length - 1] != null) {
			Result[] temp = new Result[this.resultsForThisBallotBox.length * 2];
			for (int i = 0; i < resultCount; i++) {
				temp[i] = this.resultsForThisBallotBox[i];
			}
			this.resultsForThisBallotBox = temp;
			System.out.println("the arry is doubled");
		}
	}

	public boolean cheakIfresultExist(Result r) {
		for (int i = 0; i < this.resultCount; i++) {
			if (this.resultsForThisBallotBox[i].equals(r)) {
				System.out.println("exist");// *
				return true;
			}
		}
		return false;
	}

	public void addToResult(String name) {
		copyAndMultiplyResult();
		Result r = new Result(name);
		if (cheakIfresultExist(r))
			return;
		resultsForThisBallotBox[resultCount] = r;
		this.resultCount = this.resultCount + 1;
	}

	public double voteResultsAndPercentage() {// returns percentage of votes and calculate votes for each party
		int nonVotersAmount = 0;
		for (int i = 0; i < this.citizensCount; i++) {
			if (citizenInBallotBox[i].getPartyChosen() == null)
				nonVotersAmount++;
			else
				for (int j = 0; j < resultCount; j++) {
					if (citizenInBallotBox[i].getPartyChosen() == resultsForThisBallotBox[j].getIdOrName())
						resultsForThisBallotBox[j].addVote();
				}
		}
		if (this.citizensCount > 0)
			return ((double) (this.citizensCount - nonVotersAmount) / this.citizensCount) * 100;
		else
			return 0;
	}

	public int getResultForParty(String name) {
		for (int i = 0; i < resultsForThisBallotBox.length; i++) {
			if (name == resultsForThisBallotBox[i].getIdOrName())
				return resultsForThisBallotBox[i].getNumOfvotes();
		}
		return 0;
	}

	public void showResult() {
		System.out.println("________#" + this.serialNumber + "#________");
		for (int i = 0; i < resultCount; i++) {
			resultsForThisBallotBox[i].showResult();
		}
		System.out.println("vote precentage: " + voteResultsAndPercentage() + "\n");
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(
				this.getClass().getSimpleName() + " #" + this.serialNumber + " info:" + "\n");
		str.append("location: " + this.address + "\n\n");
		str.append("the citizens in the ballotbox: \n\n");
		for (int i = 0; i < this.citizensCount; i++) {
			str.append(citizenInBallotBox[i].toString() + "\n");
		}
		str.append("vote precentage: " + this.votePresentage + "\n" + "\nresults in this ballotbox: \n");
		for (int i = 0; i < this.resultCount; i++) {
			str.append(resultsForThisBallotBox[i].toString());// *
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

}