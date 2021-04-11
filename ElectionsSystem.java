package id314022914_id206921777;

import java.util.Arrays;

public class ElectionsSystem {

	private Election[] elections;
	private int electionCounter;

	public ElectionsSystem() {
		this.elections = new Election[10];
		this.electionCounter = 0;
	}

	public void copyAndMultiplyBallotBox() {
		if (this.elections[this.elections.length - 1] != null) {
			Election[] temp = new Election[this.elections.length * 2];
			for (int i = 0; i < this.elections.length; i++) {
				temp[i] = this.elections[i];
			}
			this.elections = temp;
		}
	}

	public Election[] getElections() {
		return elections;
	}

	public int getElectionCounter() {
		return electionCounter;
	}

	public boolean createElection(int year, int month) {
		copyAndMultiplyBallotBox();
		try {
			this.elections[this.electionCounter] = new Election(year, month);
		} catch (Exception e) {
			e.getMessage();
		}
		this.electionCounter++;
		return true;
	}

	public void setElectionCounter(int electionCounter) {
		this.electionCounter = electionCounter;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("welcome to the election system \n this is our data:\n");

		for (int i = 0; i < this.electionCounter; i++) {
			str.append((i + 1) + ") " + this.elections[i].toString() + "\n");
		}
		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ElectionsSystem) {
			ElectionsSystem temp = ((ElectionsSystem) obj);
			if (!(this.elections.equals(temp)))
				return false;
		}
		return true;
	}
}
