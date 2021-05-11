package id314022914_id206921777;

public class SickSolider extends Solider implements Sickable {
	private int daysOfSickness;
	private boolean hasMask;

	public SickSolider(String name, String id, int yearOfBirth, int daysOfSickness)
			throws Exception {
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
}
