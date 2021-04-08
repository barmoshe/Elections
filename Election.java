package id314022914_id206921777;

public class Election {
	private int yearOfElections;
	private int monthOfElections;
	private Citizen[] citizens;
	private Party[] parties;
	private BallotBox[] ballotBoxes;
	private int citizenCounter;
	private int partyCounter;
	private int ballotBoxCounter;

	public Election(int yearOfElections, int monthOfElections) throws Exception {
		setMonthOfElections(monthOfElections);
		setYearOfElections(yearOfElections);
		this.citizens = new Citizen[5];
		this.parties = new Party[5];
		this.ballotBoxes = new BallotBox[5];
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

	public Citizen[] getCitizens() {
		return citizens;
	}

	public Party[] getParties() {
		return parties;
	}

	public BallotBox[] getBallotBoxes() {
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
			if (c.equals(this.citizens[i])) {
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

	private void copyAndMultiplyVoters() {
		if (this.citizens[this.citizens.length - 1] != null) {
			Citizen[] temp = new Citizen[this.citizens.length * 2];
			for (int i = 0; i < this.citizens.length; i++) {
				temp[i] = this.citizens[i];
			}
			this.citizens = temp;
		}
	}

	private boolean setVoterToBallotBox(Citizen c) {
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			if (c.getIsQuarentied()) {
				if (this.ballotBoxes[i] instanceof BallotBoxForCovid) {
					this.ballotBoxes[i].addCitizen(c);
					c.setBallotBox(ballotBoxes[i]);
					return true;
				}
			} else if (c instanceof Solider) {
				if (!(this.ballotBoxes[i] instanceof BallotBoxForCovid)) {
					this.ballotBoxes[i].addCitizen(c);
					c.setBallotBox(ballotBoxes[i]);
					return true;
				}
			} else if (c instanceof Citizen) {
				if ((!(this.ballotBoxes[i] instanceof BallotBoxForCovid))
						&& (!(this.ballotBoxes[i] instanceof BallotBoxForSoliders))) {
					this.ballotBoxes[i].addCitizen(c);
					c.setBallotBox(ballotBoxes[i]);
					return true;
				}
			}

		}
		System.out.println(" error setVoterToBallotBox ");
		return false;
	}

	public boolean checkAge(int YearOfBirth) {
		int age = this.yearOfElections - YearOfBirth;
		if (age < 18) {
			return true;
		}
		return false;
	}

	public boolean addCitizens(Citizen c) throws Exception {
		if (this.checkAge(c.getYearOfBirth())) {
			throw new Exception("too young");
		}
		int indexOfCitizen = cheakIfCitizenExist(c);
		if (indexOfCitizen != -1) {
			updateCitizen(c, indexOfCitizen);
			return true;
		}
		this.copyAndMultiplyVoters();
		int age = this.yearOfElections - c.getYearOfBirth();
		if (age <= 21)
			this.citizens[citizenCounter] = new Solider(c);
		else
			this.citizens[citizenCounter] = new Citizen(c);
		this.setVoterToBallotBox(this.citizens[citizenCounter]);
		this.citizenCounter = this.citizenCounter + 1;
		return true;
	}

	private void updateCitizen(Citizen c, int i) throws Exception {
		Citizen temp = this.citizens[i];
		temp.setName(c.getName());
		temp.setHasMask(c.getHasMask());
		temp.setIsQuarentied(c.getIsQuarentied());

	}

	public void addCitizensHadCoded(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth)
			throws Exception {
		Citizen c = new Citizen(name, id, isQuarentied, hasMask, yearOfBirth);
		this.addCitizens(c);
	}

	public boolean cheakIfPartyExist(Party p) {
		for (int i = 0; i < this.partyCounter; i++) {
			if (this.parties[i].equals(p)) {
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
		}
	}

	public boolean addParty(Party p) throws Exception {
		if (cheakIfPartyExist(p))
			return false;
		copyAndMultiplyParties();
		this.parties[partyCounter] = new Party(p);
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			BallotBox current = this.ballotBoxes[i];
			current.addToResult(p.getName());// *
		}
		this.partyCounter = this.partyCounter + 1;
		return true;
	}

	public void addPartyHardCoded(String name, PoliticalOpinion politicalOpinion) throws Exception {
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
		}
	}

	public boolean addBallotBox(BallotBox b, int choise) {
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
			return false;
		}
		for (int i = 0; i < this.partyCounter; i++) {
			String current = this.parties[i].getName();
			this.ballotBoxes[ballotBoxCounter].addToResult(current);// *
		}
		this.ballotBoxCounter = this.ballotBoxCounter + 1;
		return true;

	}

	public boolean addBallotBoxHardCoded(String address, int choise) {
		BallotBox b = new BallotBox(address);
		return addBallotBox(b, choise);
	}

	private void replaceCitizenForCandidate(Citizen c, Party p) throws Exception {
		for (int i = 0; i < this.citizenCounter; i++) {
			if (c.getId() == this.citizens[i].getId()) {
				citizens[i] = new Candidate(citizens[i]);
				((Candidate) citizens[i]).setPartyBelong(p);
				citizens[i].getBallotbox().addCitizen(citizens[i]);
				return;
			}
		}

	}

	public boolean addCandidate(Candidate c) throws Exception {
		if (checkAge(c.getYearOfBirth()))
			return false;
		int temp = cheakIfCitizenExist(c);
		if (temp != -1) {
			replaceCitizenForCandidate(this.citizens[temp], c.partyBelong);
			c.partyBelong.addCandidate((Candidate) this.citizens[temp]);
		} else {
			addCitizens(c);
			replaceCitizenForCandidate(this.citizens[citizenCounter - 1], c.partyBelong);
			c.partyBelong.addCandidate((Candidate) this.citizens[citizenCounter - 1]);
		}
		return true;
	}

	public void addCandidateHardCoded(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth,
			String partyname) throws Exception {
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

	public void showPartiesNames() {
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println("Party" + (i + 1) + " " + parties[i].getName());

		}
	}

	public void showCitizens() {
		for (int i = 0; i < this.citizenCounter; i++) {
			System.out.println("________-" + (i + 1) + "-________\n" + citizens[i].toString() + "\n");
		}
	}

	public void showBallotBoxes() {
		for (int i = 0; i < ballotBoxCounter; i++) {
			System.out.println((i + 1) + ") " + ballotBoxes[i].toString() + "\n");
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

		for (int i = 0; i < this.ballotBoxCounter; i++) {
			this.ballotBoxes[i].showResult();
		}
		System.out.println();
		for (int i = 0; i < this.partyCounter; i++) {
			System.out.println(this.parties[i].getName() + ":  " + this.parties[i].getNumOfVotes());
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
		StringBuffer str = new StringBuffer(
				"The elections in 0" + this.monthOfElections + "/" + this.yearOfElections + " info : \n" + "\n");
		str.append("The citizens: " + "\n");
		for (int i = 0; i < this.citizenCounter; i++) {
			str.append((i + 1) + ") " + this.citizens[i].toString() + "\n");
		}
		str.append("The parties: " + "\n");
		for (int i = 0; i < this.partyCounter; i++) {
			str.append((i + 1) + ") " + this.parties[i].toString() + "\n");
		}
		str.append("The ballotboxes: " + "\n");
		for (int i = 0; i < this.ballotBoxCounter; i++) {
			str.append((i + 1) + ") " + this.ballotBoxes[i].toString() + "\n");
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