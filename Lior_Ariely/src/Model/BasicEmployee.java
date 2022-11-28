package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class BasicEmployee extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicEmployee(String name, LocalDate date, String ID, String gender, Role role, Preference pref,
			int startHour) throws IdException, DateException {
		super(name, date, ID, gender, role, pref, startHour);
	}

	@Override
	public String toString() {
		return "BasicEmployee [" + super.toString() + ", salary= " + getSalary() + "NIS ]";
	}

	public int getSalary() {
		int s;
		s = 160 * 10;
		return s;
	}

}
