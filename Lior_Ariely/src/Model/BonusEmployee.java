package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class BonusEmployee extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int salesPrecent;

	public BonusEmployee(String name, LocalDate date, String ID, String gender, Role role, Preference pref,
			int startHour) throws IdException, DateException {
		super(name, date, ID, gender, role, pref, startHour);
		this.salesPrecent = 0;
	}

	@Override
	public String toString() {
		return "BonusEmployee [" + super.toString() + ", salary= " + getSalary() + "NIS ]";
	}

	public int getSalary() {
		int s;
		salesPrecent = (int) (Math.random() * 40);
		s = 160 * 10 + (10 * salesPrecent);
		return s;
	}
}
