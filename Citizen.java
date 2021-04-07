
package Elections;

public class Citizen {
	private String name;
	private String id;
	private int yearOfBirth;
	private boolean isQuarentied;
	private boolean hasMask;
	private BallotBox ballotbox;
	private String partyChosen;

	public Citizen(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth) throws Exception {
		this.hasMask = hasMask;
		setName(name);
		setId(id);
		this.isQuarentied = isQuarentied;
		setYearOfbirth(yearOfBirth);
		this.partyChosen = null;
		this.ballotbox = null;
	}

	public Citizen(Citizen c) throws Exception {
		setName(c.name);
		this.hasMask = c.hasMask;
		setId(c.id);
		this.isQuarentied = c.isQuarentied;
		setYearOfbirth(c.yearOfBirth);
		this.partyChosen = c.partyChosen;
		this.ballotbox = c.ballotbox;

	}

	public boolean setName(String name) throws Exception {
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')))
				throw new Exception("must be char between a-z or A-Z");
			break;
		}
		this.name = name;
		return true;
	}

	public boolean setId(String id) throws Exception {
		if (id.length() == 9) {
			for (int i = 0; i < id.length(); i++) {
				if (!(id.charAt(i) >= '0' && id.charAt(i) <= '9'))
					throw new Exception(" id must be numbers only ");

			}
			this.id = id;
		} else
			throw new Exception("must be 9 numbers for id");
		return true;

	}

	public boolean setYearOfbirth(int year) throws Exception {
		if (year >= 1900 && year < 2100) {
			this.yearOfBirth = year;
		} else
			throw new Exception("year must be between 1900-2100");
		return true;
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

	public boolean getIsQuarentied() {
		return this.isQuarentied;
	}

	public boolean getHasMask() {
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