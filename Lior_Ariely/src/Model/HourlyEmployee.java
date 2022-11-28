package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class HourlyEmployee extends Employee implements Serializable {

	public HourlyEmployee(String name, LocalDate date, String ID, String gender, Role role, Preference pref,
			int startHour) throws IdException, DateException {
		super(name, date, ID, gender, role, pref, startHour);
	}

	@Override
	public String toString() {
		return "HourlyEmployee [" + super.toString() + ", salary= " + getSalary() + "NIS ]";
	}

	public int getSalary() {
		int s;
		s = 25 * 9 * 10;
		return s;
	}

}
