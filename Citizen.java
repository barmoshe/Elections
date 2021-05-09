package id314022914_id206921777;

public class Citizen extends Person {

	protected boolean isQuarentied;
	protected boolean hasMask;
	protected BallotBox ballotbox;
	protected String partyChosen;

	public Citizen(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth) throws Exception {
		super(name, id, yearOfBirth);
		this.hasMask = hasMask;
		this.isQuarentied = isQuarentied;
		this.partyChosen = null;
		this.ballotbox = null;
	}

	public Citizen(Citizen c) throws Exception {
		super(c.name, c.id, c.yearOfBirth);
		this.hasMask = c.hasMask;
		this.isQuarentied = c.isQuarentied;
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
				+ ballotbox.getSerialnumber() + "\nis in Quarentine: " + this.isQuarentied + "\nhas mask?: "
				+ this.hasMask + "\nparty Chosen: " + this.partyChosen);

		return str.toString();

	}

	public String getId() {
		return id;
	}

	public boolean setPartyChosen(String party) {
		this.partyChosen = party;
		return true;
	}

	@Override
	public boolean isQuarentied() {
		return this.isQuarentied;
	}

	@Override
	public boolean hasMask() {
		return this.hasMask;
	}

	public boolean setHasMask(boolean hasMask) {
		this.hasMask = hasMask;
		return true;
	}

	public boolean setIsQuarentied(boolean isQuarentied2) {
		this.isQuarentied = isQuarentied2;
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