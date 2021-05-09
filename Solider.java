package id314022914_id206921777;

public class Solider extends Citizen implements CarryWeaponable {
	private boolean carryWeapon;
	private String typeOfWeapon;

	public Solider(Citizen c, boolean carryWeapon) throws Exception {
		super(c);
		this.typeOfWeapon = null;
		this.carryWeapon = false;
	}

	public void addweapon(String typeOfWeapon) {
		this.carryWeapon = true;

	}

	@Override
	public boolean CarryWeapon() {
		return carryWeapon;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
