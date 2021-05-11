package id314022914_id206921777;

public class Citizen extends Person {
	protected BallotBox<?> ballotbox;
	protected String partyChosen;

	public Citizen(String name, String id, int yearOfBirth) throws Exception {
		super(name, id, yearOfBirth);
		this.partyChosen = null;
		this.ballotbox = null;
	}

	public Citizen(Citizen c) throws Exception {
		super(c.name, c.id, c.yearOfBirth);
		this.partyChosen = c.partyChosen;
		this.ballotbox = c.ballotbox;
	}

	public int getYearOfBirth() {
		return this.yearOfBirth;
	}

	public boolean setBallotBox(BallotBox ballotBox) {
		this.ballotbox = ballotBox;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Citizen))
			return false;
		Citizen temp = ((Citizen) obj);
		return this.id.equals(temp.getId());
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(this.getClass().getSimpleName() + " " + this.name + " info:" + "\n");

		str.append("id: " + this.id + "\nyear of Birth: " + this.yearOfBirth + "\nballotbox: #"
				+ ballotbox.getSerialnumber() + "\nparty Chosen: " + this.partyChosen);

		return str.toString();

	}

	public String getId() {
		return id;
	}

	public boolean setPartyChosen(String party) {
		this.partyChosen = party;
		return true;
	}

	public String getName() {
		return name;
	}

	public BallotBox getBallotbox() {
		return ballotbox;
	}

	public String getPartyChosen() {
		return partyChosen;
	}

}