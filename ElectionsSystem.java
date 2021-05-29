package id314022914_id206921777;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class ElectionsSystem  implements Serializable{

	private ArrayList<Election> elections;
	private int electionCounter;
	
	public void printElectionsRound(int answer2) {
		System.out.println(elections.get(answer2));
	}
	public ElectionsSystem() {
		this.elections = new ArrayList<Election>();
		this.electionCounter = 0;
	}

	public ArrayList<Election> getElections() {
		return elections;
	}

	public int getElectionCounter() {
		return electionCounter;
	}

	public void createElection(int year, int month) throws Exception {
		try {
			Election temp = new Election(year, month);
			this.elections.add(temp);
		} catch (Exception e) {
			throw e;
		}
		this.electionCounter++;
	}

	public void setElectionCounter(int electionCounter) {
		this.electionCounter = electionCounter;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("welcome to the election system \n this is our data:\n");

		for (int i = 0; i < this.electionCounter; i++) {
			str.append((i + 1) + ") " + this.elections.get(i).toString() + "\n");
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
