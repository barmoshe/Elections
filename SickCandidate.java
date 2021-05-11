package id314022914_id206921777;

public class SickCandidate extends Candidate implements Sickable {
	private int daysOfSickness;
	private boolean hasMask;

	public SickCandidate(String name, String id, int yearOfBirth, Party party, int daysOfSickness) throws Exception {
		super(name, id, yearOfBirth, party);
		this.daysOfSickness = daysOfSickness;
	}

	public SickCandidate(Citizen c, int daysOfSickness) throws Exception {
		super(c);
		this.daysOfSickness = daysOfSickness;
	}

	public int getDaysOfSickness() {
		return this.daysOfSickness;
	}

	@Override
	public boolean hasMask() {
		return this.hasMask;
	}

	@Override
	public void setHasMask(boolean hasMask) {
		this.hasMask = hasMask;

	}

	@Override
	public int getdaysOfSickness() {
		return this.daysOfSickness;
	}
}
