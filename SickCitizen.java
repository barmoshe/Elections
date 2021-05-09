package id314022914_id206921777;

public class SickCitizen extends Citizen {
	private int daysOfSickness;

	public SickCitizen(Citizen c, int daysOfSickness) throws Exception {
		super(c);
		this.daysOfSickness = daysOfSickness;
		this.isQuarentied = true;
	}

	public int getDaysOfSickness() {
		return this.daysOfSickness;
	}

}
