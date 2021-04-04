
package Elections;

public class Citizen {
	protected String name;
	protected String id;
	protected int yearOfBirth;
	protected boolean isQuarentied;
	protected boolean hasMask;
	protected BallotBox ballotbox;
	protected String partyChosen;

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

	public void setName(String name) throws Exception {
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')))
				throw new Exception("must be char between a-z or A-Z");
			break;
		}
		this.name = name;
	}

	public void setId(String id) throws Exception {
		if (id.length() == 9) {
			for (int i = 0; i < id.length(); i++) {
				if (!(id.charAt(i) >= '0' && id.charAt(i) <= '9'))
					throw new Exception(" id must be numbers only ");

			}
			this.id = id;
		} else
			throw new Exception("must be 9 numbers for id");

	}

	public void setYearOfbirth(int year) throws Exception {
		if (year >= 1900 && year < 2100) {
			this.yearOfBirth = year;
		} else
			throw new Exception("year must be between 1900-2100");
	}

	public int getYearOfBirth() {
		return this.yearOfBirth;
	}

	public void setBallotBox(BallotBox ballotBox) {
		this.ballotbox = ballotBox;
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

	public void setPartyChosen(String party) {
		this.partyChosen = party;
	}

}