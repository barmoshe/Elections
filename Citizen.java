
package Elections;

public class Citizen {
	protected String name;
	protected String id;
	protected int yearOfBirth;
	protected boolean isQuarentied;
	protected boolean hasMask;
	protected BallotBox ballotbox;
	protected String partyChosen;

	public Citizen(String name, String id, boolean isQuarentied, boolean hasMask, int yearOfBirth) {
		this.hasMask = hasMask;
		this.name = name;
		this.id = setId(id);
		this.isQuarentied = isQuarentied;
		this.yearOfBirth = yearOfBirth;
		this.partyChosen = null;
		this.ballotbox = null;
	}

	public Citizen(Citizen c) {
		this.name = c.name;
		this.hasMask = c.hasMask;
		this.id = setId(c.id);
		this.isQuarentied = c.isQuarentied;
		this.yearOfBirth = c.yearOfBirth;
		this.partyChosen = null;
		this.ballotbox = c.ballotbox;

	}

	public String setId(String id) {
		if (id.length() == 9)// exeptions
			return id;
		else
			System.out.println("id is not correct");
		return null;
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
		Citizen temp = (Citizen) obj;
		if (temp.id == this.id)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Citizen [name=" + name + ", id=" + id + ", yearOfBirth=" + yearOfBirth + ", isQuarentied="

				+ isQuarentied + ", hasMask=" + hasMask + ", ballotbox= #" + ballotbox.getSerialnumber()
				+ ", partyChosen=" + partyChosen + "]";
	}

}