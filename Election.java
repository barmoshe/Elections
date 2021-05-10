package id314022914_id206921777;

import java.util.ArrayList;

public class Election {
	private int yearOfElections;
	private int monthOfElections;
	private SetForElections<Citizen> citizens;
	private ArrayList<Party> parties;
	private ArrayList<BallotBox> ballotBoxes;
	private int citizenCounter;
	private int partyCounter;
	private int ballotBoxCounter;

	public Election(int yearOfElections, int monthOfElections) throws Exception {
		setMonthOfElections(monthOfElections);
		setYearOfElections(yearOfElections);
		this.citizens = new SetForElections<Citizen>();
		this.parties = new ArrayList<Party>();
		this.ballotBoxes = new ArrayList<BallotBox>();
		this.citizenCounter = 0;
		this.partyCounter = 0;
		this.ballotBoxCounter = 0;

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

	public ArrayList<BallotBox> getBallotBoxes() {
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
			throw new Exception("year must be between 1900-2100");
		return true;

	}

	public boolean setMonthOfElections(int year) throws Exception {
		if (year > 0 && year < 13) {
			this.yearOfElections = year;
		} else
			throw new Exception("Month must be between 1-12");
		return true;
	}

	private boolean setVoterToBallotBox(Citizen c) throws Exception {
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			if (c.isQuarentied()) {
				if (c instanceof Solider) {
					if (this.ballotBoxes.get(i).getType() == BallotType.FOR_SICK_SOLIDERS) {
						this.ballotBoxes.get(i).addCitizen(c);
						c.setBallotBox(ballotBoxes.get(i));
						return true;
					}
				} else if (this.ballotBoxes.get(i).getType() == BallotType.FOR_SICK) {
					this.ballotBoxes.get(i).addCitizen(c);
					c.setBallotBox(ballotBoxes.get(i));
					return true;
				}

			} else if (c instanceof Solider) {
				if (this.ballotBoxes.get(i).getType() == BallotType.FOR_SOLIDERS) {
					this.ballotBoxes.get(i).addCitizen(c);
					c.setBallotBox(ballotBoxes.get(i));
					return true;
				}
			} else if (c instanceof Citizen) {
				if (this.ballotBoxes.get(i).getType() == BallotType.REGULAR) {
					this.ballotBoxes.get(i).addCitizen(c);
					c.setBallotBox(ballotBoxes.get(i));
					return true;
				}
			}

		}
		throw new Exception("error set voter to ballot box");
	}

	public int checkAge(int YearOfBirth) {
		int age = this.yearOfElections - YearOfBirth;
		if (age < 18) {
			return -1;
		}
		return age;
	}

	public boolean addCitizens(Citizen c) throws Exception {
		Citizen temp1 = null;
		int age = this.checkAge(c.getYearOfBirth());
		if (age == -1) {
			throw new Exception("too young");
		}
		int indexOfCitizen = this.citizens.exist(c);
		if (indexOfCitizen != -1) {
			this.citizens.replace(indexOfCitizen, c);
			return true;
		}
		if (age <= 21) {
			Solider temp = new Solider(c, false);
			this.citizens.add(temp);
		} else {
			temp1 = new Citizen(c);
			this.citizens.add(temp1);
		}
		this.setVoterToBallotBox(this.citizens.get(this.citizenCounter));
		this.citizenCounter = this.citizenCounter + 1;
		return true;

	}

	public void addCitizensHadCoded(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth)
			throws Exception {
		Citizen c = new Citizen(name, id, isQuarentied, hasMask, yearOfBirth);
		this.addCitizens(c);
	}

	public boolean cheakIfPartyExist(Party p) {
		return this.parties.contains(p);
	}

	public boolean addParty(Party p) throws Exception {
		if (cheakIfPartyExist(p))
			return false;
		this.parties.add(p);
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			BallotBox current = this.ballotBoxes.get(i);
			current.addToResult(p.getName());
		}
		this.partyCounter = this.partyCounter + 1;
		return true;
	}

	public void addPartyHardCoded(String name, PoliticalOpinion politicalOpinion) throws Exception {
		Party p = new Party(name, politicalOpinion);
		this.addParty(p);
	}

	public boolean addBallotBox(String address, BallotType bType) throws Exception {
		BallotBox b = new BallotBox(address, bType);

		for (int i = 0; i < this.partyCounter; i++) {
			String current = this.parties.get(i).getName();
			b.addToResult(current);
		}
		this.ballotBoxes.add(b);
		this.ballotBoxCounter = this.ballotBoxCounter + 1;
		return true;
	}

	public boolean addCandidate(Candidate c) throws Exception {
		if (checkAge(c.getYearOfBirth()) == -1)
			return false;

		int temp = citizens.existById(c.getId());
		if (temp != -1) {
			c.setBallotBox(citizens.get(temp).ballotbox);
			c.ballotbox.replace(citizens.get(temp), c);
			citizens.replace(temp, c);
		} else {
			addCitizens(c);
			c.setBallotBox(citizens.get(this.citizenCounter - 1).ballotbox);
			c.ballotbox.replace(citizens.get(citizenCounter - 1), c);
			citizens.replace(citizenCounter - 1, c);
		}
		c.getPartyBelong().addCandidate(c);
		return true;
	}

	public void addCandidateHardCoded(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth,
			String partyname) throws Exception {
		Party p = null;
		for (int i = 0; i < this.partyCounter; i++) {
			if (this.parties.get(i).getName() == partyname) {
				p = this.parties.get(i);
				break;
			}
		}
		Candidate c = new Candidate(name, id, isQuarentied, hasMask, yearOfBirth, p);
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
		for (int i = 0; i < ballotBoxCounter; i++) {
			System.out.println((i + 1) + ") " + ballotBoxes.get(i).toString() + "\n");
		}
	}

	public void calculateResultInEachBallotbox() {
		for (int i = 0; i < this.ballotBoxCounter; i++) {// calculate result in each ballotbox
			this.ballotBoxes.get(i).voteResultsAndPercentage();
		}
	}

	public void calculateResultInEachparty() {
		for (int j = 0; j < this.partyCounter; j++) {
			int temp = 0;
			for (int i = 0; i < this.ballotBoxCounter; i++) {

				temp = temp + this.ballotBoxes.get(i).getResultForParty(this.parties.get(j).getName());
			}
			this.parties.get(j).setNumOfVotes(temp);
		}
	}

	public void showResult() {

		for (int i = 0; i < this.ballotBoxCounter; i++) {
			this.ballotBoxes.get(i).showResult();
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