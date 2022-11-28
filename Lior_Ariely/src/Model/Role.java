package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Role<T extends Employee> implements Synchronizable, Changable, Serializable {
	private String name;
	private ArrayList<Employee> employees; // several employees on the same role
	private int numOfEmpls;
	private Department dep;
	private boolean sync;
	private int beginHour;
	private boolean changeMethod;
	private double efc;

	public Role(String name, Department department, boolean change, boolean sync) {
		this.name = name;
		employees = new ArrayList<Employee>();
		this.numOfEmpls = 0;
		this.dep = department;
		this.beginHour = 8;
		this.sync = sync;
		this.changeMethod = change;
		this.efc = 0;
	}

	public Role() {
		this("", null, false, false);
	}

	public String getRoleName() {
		return name;
	}

	public void addEmployee(T e) {
		employees.add(e);
		numOfEmpls++;
	}

	public void setBeginHour(int beginHour) {
		this.beginHour = beginHour;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public int getNumOfEmpls() {
		return numOfEmpls;
	}

	public Employee getEmp(int index) {
		return employees.get(index);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\nRole [name=" + name + ", sync=" + sync + ", beginHour=" + beginHour
				+ ", changeMethod=" + changeMethod);
		if (numOfEmpls == 0)
			sb.append(" ==> There are no workers");
		else
			sb.append(", employees= ");
		for (int i = 0; i < numOfEmpls; i++) {
			sb.append("\n" + employees.get(i).toString());
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

	public Department getDep() {
		return dep;
	}

	public double getEfficiency() {
		for (int i = 0; i < numOfEmpls; i++) {
			efc = efc + employees.get(i).getEfc();
		}
		return efc;
	}

	public int getBeginHour() {
		return beginHour;
	}
}
