package Model;

import java.io.Serializable;
import java.time.LocalDate;

enum Preference {
	START_EARLY, START_LATE, HOME, NO_CHANGE;
}

public class Employee implements Synchronizable, Changable, Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected String name;
	protected LocalDate birthDate;
	protected String Id;
	protected String gender;
	protected Role role;
	protected int startHour;
	protected Preference pref;
	protected int salary;
	protected double efc;

	public Employee(String name, LocalDate date, String ID, String gender, Role role, Preference p, int startHour)
			throws IdException, DateException {
		this.name = name;
		setDateOfBirth(this.birthDate = date);
		setID(ID);
		this.gender = gender;
		this.role = role;
		this.startHour = startHour;
		setPref(p);
		this.salary = 0;
		this.efc = 0;
	}

	public Employee() throws IdException, DateException {
		this("", null, "999999999", "", null, Preference.NO_CHANGE, 8);
	}

	public boolean setPref(Preference p) {
		boolean valid = true;
		if (p != null)
			this.pref = p;
		else
			this.pref = Preference.NO_CHANGE;
		return valid;
	}

	public boolean setDateOfBirth(LocalDate date) throws DateException {
		if (date.isBefore(LocalDate.of(2003, 8, 11)))
			this.birthDate = date;
		else {
			throw new DateException();
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return Id;
	}

	public boolean setID(String id) throws IdException {
		boolean valid = false;
		if (id.length() != 9)
			throw new IdException();
		else {
			for (int i = 0; i < 9; i++) {
				if (id.charAt(i) >= '0' && id.charAt(i) <= '9') {
					valid = true;
				} else
					throw new IdException();
			}
			if (valid)
				this.Id = id;
		}
		return true;
	}

	public int getStartHour() {
		return startHour;
	}

	public Preference getPref() {
		return pref;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", birthDate=" + birthDate.toString() + ", Id=" + Id + ", gender=" + gender
				+ ", startHour=" + startHour + ", pref=" + pref.name() + ", role=" + role.getRoleName();
	}

	@Override
	public boolean isSync() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSync(boolean s) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isChangable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setChangable(boolean c) {
		// TODO Auto-generated method stub
	}

	public Role getRole() {
		return role;
	}

	public double getEfc() {
		return efc;
	}

	public String employeeEfncy(int h) {
		StringBuffer sb = new StringBuffer();
		if (pref == Preference.HOME)
			efc = 9 * (0.1);
		else {
			efc = Math.abs(h - startHour);
			efc = efc * (1.2);
		}
		sb.append(toString() + " ---> Hours by precentage of efficiency: " + efc + "\n");
		return sb.toString();
	}

	public String employeeEfcnyDepRole(int hour) {
		StringBuffer sb = new StringBuffer();
		efc = Math.abs(hour - startHour);
		efc = (efc * (-0.2));
		sb.append(toString() + " ---> Hours by precentage of efficiency: " + efc + "\n");
		return sb.toString();

	}

}
