package Elections;

import java.util.Arrays;

public class Election {
	private int yearOfElections;
	private int monthOfElections;
	protected Citizen[] citizens;
	protected Party[] parties;
	private BallotBox[] ballotBoxes;
	protected int citizenCounter;
	private int partyCounter;
	private int ballotBoxCounter;

	public Election(int yearOfElections, int monthOfElections) {
		this.monthOfElections = monthOfElections;
		this.yearOfElections = yearOfElections;
		this.citizens = new Citizen[10];
		this.parties = new Party[10];
		this.ballotBoxes = new BallotBox[10];
		this.citizenCounter = 0;
		this.partyCounter = 0;
		this.ballotBoxCounter = 0;

	}

	public int cheakIfCitizenExist(Citizen c) {
		for (int i = 0; i < this.citizenCounter; i++) {
			if (this.citizens[i].equals(c)) {
				System.out.println("exist");// *
				return i;
			}
		}
		return -1;
	}

	private void copyAndMultiplyVoters() {
		if (this.citizens[this.citizens.length - 1] != null) {
			Citizen[] temp = new Citizen[this.citizens.length * 2];
			for (int i = 0; i < this.citizens.length; i++) {
				temp[i] = this.citizens[i];
			}
			this.citizens = temp;
			System.out.println("the arry is doubled");
		}
	}

	private void setVoterToBallotBox(Citizen c) {
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			if (c.isQuarentied) {
				if (this.ballotBoxes[i] instanceof BallotBoxForCovid) {
					this.ballotBoxes[i].addCitizen(c);
					c.setBallotBox(ballotBoxes[i]);
					return;
				}
			} else if (c instanceof Solider) {
				if (!(this.ballotBoxes[i] instanceof BallotBoxForCovid)) {
					this.ballotBoxes[i].addCitizen(c);
					c.setBallotBox(ballotBoxes[i]);
					return;
				}
			} else if (c instanceof Citizen) {
				if ((!(this.ballotBoxes[i] instanceof BallotBoxForCovid))
						&& (!(this.ballotBoxes[i] instanceof BallotBoxForSoliders))) {
					this.ballotBoxes[i].addCitizen(c);
					c.setBallotBox(ballotBoxes[i]);
					return;
				}
			}
		}
		System.out.println(" error setVoterToBallotBox ");
	}

	public boolean checkAge(int YearOfBirth) {
		int age = this.yearOfElections - YearOfBirth;
		if (age < 18) {// TODO: exception throw.
			System.out.println("not old enoght");
			return true;
		}
		return false;
	}

	public void addCitizens(Citizen c) {
		if (this.checkAge(c.getYearOfBirth()))
			return;

		if (cheakIfCitizenExist(c) != -1)
			return;
		this.copyAndMultiplyVoters();
		int age = this.yearOfElections - c.getYearOfBirth();
		if (age <= 21)
			this.citizens[citizenCounter] = new Solider(c);
		else
			this.citizens[citizenCounter] = new Citizen(c);
		this.setVoterToBallotBox(this.citizens[citizenCounter]);
		this.citizenCounter = this.citizenCounter + 1;

	}

	public void addCitizensHadCoded(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth) {
		Citizen c = new Citizen(name, id, isQuarentied, hasMask, yearOfBirth);
		this.addCitizens(c);
	}

	public boolean cheakIfPartyExist(Party p) {
		for (int i = 0; i < this.partyCounter; i++) {
			if (this.parties[i].equals(p)) {
				System.out.println("exist");// *
				return true;
			}
		}
		return false;
	}

	public void copyAndMultiplyParties() {
		if (this.parties[this.parties.length - 1] != null) {
			Party[] temp = new Party[this.parties.length * 2];
			for (int i = 0; i < this.parties.length; i++) {
				temp[i] = this.parties[i];
			}
			this.parties = temp;
			System.out.println("the arry is doubled");
		}
	}

	public void addParty(Party p) {
		if (cheakIfPartyExist(p))
			return;
		copyAndMultiplyParties();
		this.parties[partyCounter] = new Party(p);
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			BallotBox current = this.ballotBoxes[i];
			current.addToResult(p.getName());// *
		}
		this.partyCounter = this.partyCounter + 1;
	}

	public void addPartyHardCoded(String name, PoliticalOpinion politicalOpinion) {
		Party p = new Party(name, politicalOpinion);
		this.addParty(p);
	}

	public void copyAndMultiplyBallotBox() {
		if (this.ballotBoxes[this.ballotBoxes.length - 1] != null) {
			BallotBox[] temp = new BallotBox[this.ballotBoxes.length * 2];
			for (int i = 0; i < this.ballotBoxes.length; i++) {
				temp[i] = this.ballotBoxes[i];
			}
			this.ballotBoxes = temp;
			System.out.println("the arry is doubled");
		}
	}

	public void addBallotBox(BallotBox b, int choise) {
		copyAndMultiplyBallotBox();
		switch (choise) {
		case 1:
			this.ballotBoxes[ballotBoxCounter] = new BallotBox(b);
			break;
		case 2:
			this.ballotBoxes[ballotBoxCounter] = new BallotBoxForSoliders(b);
			break;
		case 3:
			this.ballotBoxes[ballotBoxCounter] = new BallotBoxForCovid(b);
			break;
		default:
			System.out.println("ok bye");
			break;
		}
		for (int i = 0; i < this.partyCounter; i++) {
			String current = this.parties[i].getName();
			this.ballotBoxes[ballotBoxCounter].addToResult(current);// *
		}
		this.ballotBoxCounter = this.ballotBoxCounter + 1;
		;
	}

	public void addBallotBoxHardCoded(String address, int choise) {
		BallotBox b = new BallotBox(address);
		addBallotBox(b, choise);
	}

	private void replaceCitizenForCandidate(Citizen c, Party p, int i) {
		if (c.id == this.citizens[i].id) {
			citizens[i] = new Candidate(citizens[i]);
			((Candidate) citizens[i]).setPartyBelong(p);
			citizens[i].ballotbox.addCitizen(citizens[i]);
			return;
		}
	}

	public void addCandidate(Candidate c) {
		if (checkAge(c.getYearOfBirth()))
			return;
		int temp = cheakIfCitizenExist(c);
		if (temp != -1) {
			replaceCitizenForCandidate(this.citizens[temp], c.partyBelong, temp);
			c.partyBelong.addCandidate((Candidate) this.citizens[temp]);
		} else {
			addCitizens(c);
			replaceCitizenForCandidate(this.citizens[citizenCounter - 1], c.partyBelong, citizenCounter - 1);
			c.partyBelong.addCandidate((Candidate) this.citizens[citizenCounter - 1]);
		}
	}

	public void addCandidateHardCoded(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth,
			String partyname) {
		Party p = null;
		for (int i = 0; i < this.partyCounter; i++) {
			if (this.parties[i].getName() == partyname)
				p = this.parties[i];
		}

		Candidate c = new Candidate(name, id, isQuarentied, hasMask, yearOfBirth, p);
		this.addCandidate(c);
	}

	public void showParties() {
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println("Party" + (i + 1) + " " + parties[i].toString());

		}
	}

	public void showCitizens() {
		for (int i = 0; i < this.citizenCounter; i++) {
			System.out.println("Citizen" + (i + 1) + " " + citizens[i].toString());
		}
	}

	public void showBallotBoxes() {
		for (int i = 0; i < ballotBoxCounter; i++) {
			System.out.println("ballot box " + (i + 1) + " " + ballotBoxes[i].toString());
		}
	}

	public void calculateResultInEachBallotbox() {
		for (int i = 0; i < this.ballotBoxCounter; i++) {// calculate result in each ballotbox
			this.ballotBoxes[i].voteResultsAndPercentage();
		}
	}

	public void calculateResultInEachparty() {
		for (int j = 0; j < this.partyCounter; j++) {
			int temp = 0;
			for (int i = 0; i < this.ballotBoxCounter; i++) {

				temp = temp + this.ballotBoxes[i].getResultForParty(this.parties[j].getName());
			}
			this.parties[j].setNumOfVotes(temp);
		}
	}

	public void showResult() {
		calculateResultInEachparty();

		for (int i = 0; i < this.ballotBoxCounter; i++) {
			this.ballotBoxes[i].showResult();
		}
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println(this.parties[i].getName() + ":  " + this.parties[i].numOfVotes);
		}
	}

	public void electionStart() {
		for (int i = 0; i < this.partyCounter; i++) {// prime Election For Party
			this.parties[i].primeElectionForParty();
		}
		this.calculateResultInEachBallotbox();
		this.calculateResultInEachparty();

	}

	@Override
	public String toString() {
		return "Elections [yearOfElections=" + yearOfElections + ", monthOfElections=" + monthOfElections + ", voters="
				+ Arrays.toString(citizens) + ", parties=" + Arrays.toString(parties) + ", ballotBoxes="
				+ Arrays.toString(ballotBoxes) + ", citizenCounter=" + citizenCounter + ", partyCounter=" + partyCounter
				+ ", ballotBoxCounter=" + ballotBoxCounter + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Election))
			return false;
		Election temp = (Election) obj;
		if (temp.monthOfElections == this.monthOfElections && this.yearOfElections == temp.yearOfElections)
			return true;
		else
			return false;

	}
}
