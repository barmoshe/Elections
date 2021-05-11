package id314022914_id206921777;

public class Solider extends Citizen {
	private boolean carryWeapon;
	private String typeOfWeapon;

	public Solider(Citizen c) throws Exception {
		super(c);
		this.typeOfWeapon = null;
		this.carryWeapon = false;
	}

	public Solider(String name, String id, int yearOfBirth) throws Exception {
		super(name, id, yearOfBirth);
		this.typeOfWeapon = null;
		this.carryWeapon = false;
	}

	public void addWeaponToSoliders(String typeOfWeapon) {
		this.typeOfWeapon = typeOfWeapon;
		this.carryWeapon = true;

	}

	public String CarryWeapon() {
		if (this.typeOfWeapon != null)
			return this.typeOfWeapon;
		else
			return " no weapon";
	}

	@Override
	public String toString() {
		return super.toString() + "\nweapon: " + this.CarryWeapon();
	}
}
