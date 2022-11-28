package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Synchronizable, Changable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String depName;
	private ArrayList<Role<Employee>> roles;
	private int numOfRoles;
	private boolean sync;
	private int beginHour;
	private boolean changeMethod;
	private Company company;
	private double e;

	public Department(String name, Company company, boolean change, boolean sync) {
		roles = new ArrayList<Role<Employee>>();
		this.numOfRoles = 0;
		this.depName = name;
		this.company = company;
		this.sync = sync;
		this.beginHour = 8;
		this.changeMethod = change;
	}

	public Department() {
		this("", null, false, false);
	}

	public String getDepName() {
		return depName;
	}

	public int getNumOfRoles() {
		return numOfRoles;
	}

	public int getBeginHour() {
		return beginHour;
	}

	public ArrayList<Role<Employee>> getRoles() {
		return roles;
	}

	public void addRole(Role<Employee> r) {
		roles.add(r);
		numOfRoles++;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\nDepartment [depName=" + depName + ", syncable =" + sync + ", beginHour="
				+ beginHour + ", changeMethod=" + changeMethod + ", numOfRoles=" + numOfRoles);
		for (int i = 0; i < numOfRoles; i++) {
			sb.append(roles.get(i).toString());
		}
		return sb.toString();
	}

	@Override
	public boolean isSync() {
		return sync;
	}

	@Override
	public void setSync(boolean s) {
		this.sync = s;
	}

	@Override
	public boolean isChangable() {
		return changeMethod;
	}

	@Override
	public void setChangable(boolean c) {
		this.changeMethod = c;
	}

	public void setBeginHour(int beginHour) {
		this.beginHour = beginHour;
	}

	public double efc(int roleIndex, int empIndex) {
		e = Math.abs(beginHour - (roles.get(roleIndex).getEmployees().get(empIndex).getStartHour()));
		e = e * (-0.2);
		return e;
	}

	public double getE() {
		return e;
	}

	public String depEfcny() {
		StringBuffer sb = new StringBuffer();
		double e;
		for (int i = 0; i < numOfRoles; i++) {
			for (int j = 0; j < getRoles().get(i).getNumOfEmpls(); j++) {
				sb.append(getRoles().get(i).getEmployees().get(j).toString()
						+ " ---> Hours  by precentage of efficiency: " + efc(i, j) + "\n");
			}
		}
		return sb.toString();
	}

}
