package id314022914_id206921777;

import id314022914_id206921777.exceptions.NegativeNumException;

public class SickCandidate extends Candidate implements Sickable {
	private int daysOfSickness;
	private boolean hasMask;

	public SickCandidate(String name, String id, int yearOfBirth, Party party, int daysOfSickness) throws Exception {
		super(name, id, yearOfBirth, party);
		if (daysOfSickness >= 0)
			this.daysOfSickness = daysOfSickness;
		else
			throw new NegativeNumException();
	}

	public SickCandidate(SickCitizen c) throws Exception {
		super(c);
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

	@Override
	public String toString() {
		return super.toString() + "\ndays of sickness: " + this.daysOfSickness;
	}

}
