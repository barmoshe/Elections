package id314022914_id206921777;

import java.util.ArrayList;
import java.util.Vector;

import id314022914_id206921777.exceptions.BallotBoxException;
import id314022914_id206921777.exceptions.ValidMonthException;
import id314022914_id206921777.exceptions.ValidYearException;

public class Election {
	private final int ballotTypesAmount = 4;
	private int yearOfElections;
	private int monthOfElections;
	private SetForElections<Citizen> citizens;
	private ArrayList<Party> parties;
	private Vector<ArrayList<BallotBox>> ballotBoxes;
	private int citizenCounter;
	private int partyCounter;
	private int ballotBoxCounter;

	public Election(int yearOfElections, int monthOfElections) throws Exception {
		setMonthOfElections(monthOfElections);
		setYearOfElections(yearOfElections);
		this.citizens = new SetForElections<Citizen>();
		this.parties = new ArrayList<Party>();
		this.ballotBoxes = new Vector<ArrayList<BallotBox>>();
		this.setTypesOfBallotBox();
		this.citizenCounter = 0;
		this.partyCounter = 0;
		this.ballotBoxCounter = 0;

	}

	private void setTypesOfBallotBox() {
		for (int i = 0; i < this.ballotTypesAmount; i++) {
			ArrayList<BallotBox> a = new ArrayList<BallotBox>();
			this.ballotBoxes.add(a);
		}
	}

	public int getYearOfElections() {
		return yearOfElections;
	}

	public int getMonthOfElections() {
		return monthOfElections;
	}

	public SetForElections<Citizen> getCitizens() {
		return citizens;
	}

	public ArrayList<Party> getParties() {
		return parties;
	}

	public Vector<ArrayList<BallotBox>> getBallotBoxes() {
		return ballotBoxes;
	}

	public int getCitizenCounter() {
		return citizenCounter;
	}

	public int getPartyCounter() {
		return partyCounter;
	}

	public int getBallotBoxCounter() {
		return ballotBoxCounter;
	}

	public Election() {
	}

	public int cheakIfCitizenExist(Citizen c) {
		for (int i = 0; i < this.citizenCounter; i++) {
			if (c.equals(this.citizens.get(i))) {
				return i;
			}
		}
		return -1;
	}

	public boolean setYearOfElections(int year) throws Exception {
		if (year >= 1900 && year < 2100) {
			this.yearOfElections = year;
		} else
			throw new ValidYearException();
		return true;

	}

	public boolean setMonthOfElections(int year) throws Exception {
		if (year > 0 && year < 13) {
			this.yearOfElections = year;
		} else
			throw new ValidMonthException();
		return true;
	}

	private boolean setVoterToBallotBox(Citizen c) throws Exception {
		int random;
		if (c instanceof Sickable) {
			if (c instanceof SickSolider) {
				random = (int) (Math.random() * this.ballotBoxes.get(0).size());
				this.ballotBoxes.get(0).get(random).addCitizen(c);
				c.setBallotBox(ballotBoxes.get(0).get(random));
				return true;

			} else
				random = (int) (Math.random() * this.ballotBoxes.get(1).size());

			this.ballotBoxes.get(1).get(random).addCitizen(c);
			c.setBallotBox(ballotBoxes.get(1).get(random));
			return true;

		} else if (c instanceof Solider) {
			random = (int) (Math.random() * this.ballotBoxes.get(2).size());
			this.ballotBoxes.get(2).get(random).addCitizen(c);
			c.setBallotBox(ballotBoxes.get(2).get(random));
			return true;
		} else {
			random = (int) (Math.random() * this.ballotBoxes.get(3).size());
			this.ballotBoxes.get(3).get(random).addCitizen(c);
			c.setBallotBox(ballotBoxes.get(3).get(random));
			return true;

		}

	}

	public boolean addCitizens(Citizen c) throws Exception {
		int indexOfCitizen = this.citizens.existById(c.getId());
		if (indexOfCitizen != -1) {
			this.citizens.replace(indexOfCitizen, c);
			return true;
		} else
			citizens.add(c);

		this.setVoterToBallotBox(this.citizens.get(this.citizenCounter));
		this.citizenCounter = this.citizenCounter + 1;
		return true;

	}

	public void addSoliderHadCoded(String name, String id, int yearOfBirth) throws Exception {
		Solider s = new Solider(name, id, yearOfBirth);
		this.addCitizens(s);
	}

	public void addCitizenHadCoded(String name, String id, int yearOfBirth) throws Exception {
		Citizen c = new Citizen(name, id, yearOfBirth);
		this.addCitizens(c);
	}

	public void addSickCitizenHadCoded(String name, String id, int yearOfBirth, int daysOfSickness) throws Exception {
		SickCitizen c = new SickCitizen(name, id, yearOfBirth, daysOfSickness);
		this.addCitizens(c);
	}

	public void addSickSoliderHadCoded(String name, String id, int yearOfBirth, int daysOfSickness) throws Exception {
		SickSolider c = new SickSolider(name, id, yearOfBirth, daysOfSickness);
		this.addCitizens(c);
	}

	public boolean cheakIfPartyExist(Party p) {
		return this.parties.contains(p);
	}

	public boolean addParty(Party p) throws Exception {
		if (cheakIfPartyExist(p))
			return false;
		this.parties.add(p);
		for (int j = 0; j < this.ballotTypesAmount; j++) {
			for (int i = 0; i < this.ballotBoxCounter; i++) {
				BallotBox<Citizen> current = this.ballotBoxes.get(j).get(i);
				current.addToResult(p.getName());
			}
		}
		this.partyCounter = this.partyCounter + 1;
		return true;
	}

	public void addPartyHardCoded(String name, PoliticalOpinion politicalOpinion) throws Exception {
		Party p = new Party(name, politicalOpinion);
		this.addParty(p);
	}

	public boolean addBallotBox(String address, BallotType bType) throws Exception {
		switch (bType) {
		case FOR_SICK_SOLIDERS:
			BallotBox<SickSolider> b3 = new BallotBox<SickSolider>(address, bType);
			for (int i = 0; i < this.partyCounter; i++) {
				String current = this.parties.get(i).getName();
				b3.addToResult(current);
			}
			this.ballotBoxes.get(0).add(b3);
			this.ballotBoxCounter = this.ballotBoxCounter + 1;
			return true;
		case FOR_SICK:
			BallotBox<SickCitizen> b = new BallotBox<SickCitizen>(address, bType);
			for (int i = 0; i < this.partyCounter; i++) {
				String current = this.parties.get(i).getName();
				b.addToResult(current);
			}
			this.ballotBoxes.get(1).add(b);
			this.ballotBoxCounter = this.ballotBoxCounter + 1;
			return true;
		case FOR_SOLIDERS:
			BallotBox<Solider> b2 = new BallotBox<Solider>(address, bType);
			for (int i = 0; i < this.partyCounter; i++) {
				String current = this.parties.get(i).getName();
				b2.addToResult(current);
			}
			this.ballotBoxes.get(2).add(b2);
			this.ballotBoxCounter = this.ballotBoxCounter + 1;
			return true;
		case REGULAR:
			BallotBox<Citizen> b1 = new BallotBox<Citizen>(address, bType);
			for (int i = 0; i < this.partyCounter; i++) {
				String current = this.parties.get(i).getName();
				b1.addToResult(current);
			}
			this.ballotBoxes.get(3).add(b1);
			this.ballotBoxCounter = this.ballotBoxCounter + 1;
			return true;

		default:
			return false;
		}

	}

	public boolean addCandidate(Candidate c) throws Exception {

		int temp = citizens.existById(c.getId());
		if (temp != -1) {
			c.setBallotBox(citizens.get(temp).ballotbox);
			c.ballotbox.PromotCitizenToCand(citizens.get(temp), c);
			citizens.replace(temp, c);
		} else {
			addCitizens(c);
			c.setBallotBox(citizens.get(this.citizenCounter - 1).ballotbox);
			c.ballotbox.PromotCitizenToCand(citizens.get(citizenCounter - 1), c);
			citizens.replace(citizenCounter - 1, c);
		}
		c.getPartyBelong().addCandidate(c);
		return true;
	}

	public void addCandidateHardCoded(String name, String id, int yearOfBirth, String partyname) throws Exception {
		Party p = null;
		for (int i = 0; i < this.partyCounter; i++) {
			if (this.parties.get(i).getName() == partyname) {
				p = this.parties.get(i);
				break;
			}
		}
		Candidate c = new Candidate(name, id, yearOfBirth, p);
		this.addCandidate(c);
	}

	public void addSickCandidateHardCoded(String name, String id, int yearOfBirth, String partyname, int daysOfSickness)
			throws Exception {
		Party p = null;
		for (int i = 0; i < this.partyCounter; i++) {
			if (this.parties.get(i).getName() == partyname) {
				p = this.parties.get(i);
				break;
			}
		}
		SickCandidate c = new SickCandidate(name, id, yearOfBirth, p, daysOfSickness);
		this.addCandidate(c);
	}

	public void showParties() {
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println("Party" + (i + 1) + " " + parties.get(i).toString());

		}
	}

	public void showPartiesNames() {
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println("Party number " + (i + 1) + "- " + parties.get(i).getName());

		}
	}

	public void showCitizens() {
		System.out.println(this.citizens.toString());

	}

	public void showBallotBoxes() {
		int tempCounter = 1;
		for (int i = 0; i < this.ballotTypesAmount; i++) {
			for (int j = 0; j < this.ballotBoxes.get(i).size(); j++) {
				System.out.println((tempCounter) + ") " + ballotBoxes.get(i).get(j).toString() + "\n");
				tempCounter++;
			}
		}
	}

	public void calculateResultInEachBallotbox() {
		for (int i = 0; i < this.ballotTypesAmount; i++) {
			for (int j = 0; j < this.ballotBoxes.get(i).size(); j++) {// calculate result in each ballotbox
				this.ballotBoxes.get(i).get(j).voteResultsAndPercentage();
			}
		}
	}

	public void calculateResultInEachparty() {
		for (int j = 0; j < this.partyCounter; j++) {
			int temp = 0;
			for (int i = 0; i < this.ballotTypesAmount; i++) {
				for (int k = 0; k < this.ballotBoxes.get(i).size(); k++) {
					temp = temp + this.ballotBoxes.get(i).get(k).getResultForParty(this.parties.get(k).getName());
				}
			}
			this.parties.get(j).setNumOfVotes(temp);
		}
	}

	public void showResult() {

		for (int i = 0; i < this.ballotTypesAmount; i++) {
			for (int k = 0; k < this.ballotBoxes.get(i).size(); k++) {
				this.ballotBoxes.get(i).get(k).showResult();
			}
		}
		System.out.println();
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println(this.parties.get(i).getName() + ":  " + this.parties.get(i).getNumOfVotes());
		}
	}

	public void electionStart() {
		for (int i = 0; i < this.partyCounter; i++) {// prime Election For Party
			this.parties.get(i).primeElectionForParty();
		}
		this.calculateResultInEachBallotbox();
		this.calculateResultInEachparty();

	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(
				"The elections in 0" + this.monthOfElections + "/" + this.yearOfElections + " info : \n" + "\n");
		str.append("The citizens: " + "\n");
		for (int i = 0; i < this.citizenCounter; i++) {
			str.append((i + 1) + ") " + this.citizens.get(i).toString() + "\n");
		}
		str.append("The parties: " + "\n");
		for (int i = 0; i < this.partyCounter; i++) {
			str.append((i + 1) + ") " + this.parties.get(i).toString() + "\n");
		}
		str.append("The ballotboxes: " + "\n");
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			str.append((i + 1) + ") " + this.ballotBoxes.get(i).toString() + "\n");
		}
		return str.toString();

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