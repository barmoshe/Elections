package id314022914_id206921777;

public class SickCitizen extends Citizen implements Sickable {
	private int daysOfSickness;
	private boolean hasMask;

	public SickCitizen(Citizen c, int daysOfSickness) throws Exception {
		super(c);
		this.daysOfSickness = daysOfSickness;
	}

	public SickCitizen(String name, String id, int yearOfBirth, int daysOfSickness) throws Exception {
		super(name, id, yearOfBirth);
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

	@Override
	public String toString() {
		return super.toString() + "\ndays of sickness: " + this.daysOfSickness;
	}
}
