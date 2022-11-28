package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Department> departments;
	private int numOfDepartments;
	private LocalDate dateOfFoundation;
	private String companyName;
	private ArrayList<Employee> allEmployees;
	private int numOfEmployees;
	private ArrayList<Role<Employee>> allRoles;
	private int numOfRoles;

	public Company(String date, String name) {
		departments = new ArrayList<Department>();
		allEmployees = new ArrayList<Employee>();
		allRoles = new ArrayList<Role<Employee>>();
		this.numOfRoles = 0;
		this.numOfEmployees = 0;
		this.numOfDepartments = 0;
		this.companyName = name;
		setDate(this.dateOfFoundation = LocalDate.parse(date));
	}

	public Company() {
		this("9999-01-01", "");
	}

	public boolean setDate(LocalDate date) {
		boolean valid = true;
		if (date.isBefore(LocalDate.now()))
			this.dateOfFoundation = date;
		else {
			System.out.println("Invalid date");
			valid = false;
			this.dateOfFoundation = LocalDate.parse("9999-01-01");
		}
		return valid;
	}

	public ArrayList<Department> getDepartments() {
		return departments;
	}

	public void addDepartment(String name, Company company, boolean change, boolean sync) throws DoubleNameException {
		if (checkName(name)) {
			departments.add(new Department(name, company, change, sync));
			numOfDepartments++;
		} else
			throw new DoubleNameException();
	}

	public boolean checkName(String name) {
		boolean valid = true;
		for (int i = 0; i < numOfDepartments; i++) {
			if (name.equals(departments.get(i).getDepName()))
				valid = false;
		}
		return valid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void addRole(String name, String dep, boolean change, boolean sync) throws DoubleNameException {
		int depIndex = 0;
		if (findDep(dep) > -1) {
			depIndex = findDep(dep);
			checkRoleName(name);
			allRoles.add(new Role(name, departments.get(depIndex), change, sync));
			numOfRoles++;
			departments.get(depIndex).addRole(allRoles.get(numOfRoles - 1));
		}
	}

	public boolean checkRoleName(String name) throws DoubleNameException {
		for (int i = 0; i < numOfDepartments; i++) {
			for (int j = 0; j < departments.get(i).getNumOfRoles(); j++) {
				if (name.equals(departments.get(i).getRoles().get(j).getRoleName()))
					throw new DoubleNameException();
				return false;
			}
		}
		return true;
	}

	public int findDep(String dep) {
		for (int i = 0; i < numOfDepartments; i++) {
			if (dep.equals(departments.get(i).getDepName()))
				return i;
		}
		return -1;
	}

	public void addEmployee(String name, LocalDate date, String Id, String gender, String role, String type, String p,
			int hour) throws IdException, DateException {
		int roleIndex = 0;
		Preference pref = whichPreference(p);
		if (date.isBefore(LocalDate.of(2003, 8, 11))) {
			if (findRole(role) > -1) {
				if (checkId(Id)) {
					roleIndex = findRole(role);
					if (type.equals("Basic Employee")) {
						allEmployees
								.add(new BasicEmployee(name, date, Id, gender, allRoles.get(roleIndex), pref, hour));
					} else if (type.equals("Bonus Employee")) {
						allEmployees
								.add(new BonusEmployee(name, date, Id, gender, allRoles.get(roleIndex), pref, hour));
					} else
						allEmployees
								.add(new HourlyEmployee(name, date, Id, gender, allRoles.get(roleIndex), pref, hour));
					numOfEmployees++;
					NotifyRole(allEmployees.get(numOfEmployees - 1), roleIndex);

				}
			}
		} else
			throw new DateException();
	}

	public void changeDepmethod(String name, boolean sync, int newHour) {
		int index = findDep(name);
		departments.get(index).setBeginHour(newHour);
		departments.get(index).setSync(sync);
	}

	public void changeRoleMethod(String name, boolean sync, int newHour) {
		int index = findRole(name);
		allRoles.get(index).setSync(sync);
		allRoles.get(index).setBeginHour(newHour);
	}

	private Preference whichPreference(String p) {
		if (p.equals("No Change"))
			return Preference.NO_CHANGE;
		if (p.equals("Start Early"))
			return Preference.START_EARLY;
		if (p.equals("Start Late"))
			return Preference.START_LATE;
		if (p.equals("Home"))
			return Preference.HOME;
		return null;
	}

	private boolean checkId(String id) throws IdException {
		int i;
		for (i = 0; i < numOfEmployees; i++) {
			if (id.equals(allEmployees.get(i).getId()))
				throw new IdException();
		}
		return true;
	}

	private void NotifyRole(Employee e, int index) {
		allRoles.get(index).addEmployee(e);
	}

	public int findRole(String role) {
		for (int i = 0; i < numOfRoles; i++) {
			if (role.equals(allRoles.get(i).getRoleName()))
				return i;
		}
		return -1;
	}

	public ArrayList<Role<Employee>> getAllRoles() {
		return allRoles;
	}

	public boolean setDeps(ArrayList<Department> d) {
		this.departments = d;
		this.numOfDepartments = d.size();
		return true;
	}

	public boolean setRoles(ArrayList<Role<Employee>> r) {
		this.allRoles = r;
		this.numOfRoles = r.size();
		return true;
	}

	public boolean setEmployees(ArrayList<Employee> e) {
		this.allEmployees = e;
		this.numOfEmployees = e.size();
		return true;
	}

/*	public String efficiency2() {
		double sumDep = 0, sumRole = 0;
		;
		StringBuffer sb = new StringBuffer("The total efficiency is: \n");
		for (int i = 0; i < numOfDepartments; i++) {
			if (departments.get(i).isSync()) {
				sb.append(departments.get(i).depEfcny());
				sumDep = sumDep + departments.get(i).getE();
			} else {
				for (int j = 0; j < numOfRoles; j++) {
					if (allRoles.get(j).isSync()) {
						sb.append(allRoles.get(j).roleEfcny());
						sumRole = sumRole + allRoles.get(j).getEfc();
					} else {
						for (int k = 0; k < numOfEmployees; k++) {
							sb.append(allEmployees.get(k).employeeEfcny(departments.get(i).getBeginHour()));
						}
					}
					sb.append("The total hours of effiency in the " + allRoles.get(j).getRoleName() + " role is: "
							+ sumRole + "\n");
					sumRole = 0;
				}
			}
			sb.append("The total hours of effiency in the " + departments.get(i).getDepName() + " department is: "
					+ sumDep + "\n");
			sumDep = 0;
		}
		return sb.toString();
	} */

	public String efficiency() {
		StringBuffer sb = new StringBuffer("The total efficiency is: \n");
		double sumRole = 0;
		double sumDep = 0;
		for (int i = 0; i < numOfEmployees; i++) {
			if (allEmployees.get(i).getRole().getDep().isSync() == true)
				sb.append(allEmployees.get(i)
						.employeeEfcnyDepRole(allEmployees.get(i).getRole().getDep().getBeginHour()));
			else if (allEmployees.get(i).getRole().isSync() == true)
				sb.append(allEmployees.get(i).employeeEfcnyDepRole(allEmployees.get(i).getRole().getBeginHour()));
			else
				sb.append(allEmployees.get(i).employeeEfncy(allEmployees.get(i).getRole().getDep().getBeginHour()));
		}
		
		for (int j = 0; j < numOfDepartments; j++) {
			sb.append("\n");
			for (int k = 0; k < departments.get(j).getNumOfRoles() ; k++) {
				sumRole = departments.get(j).getRoles().get(k).getEfficiency();
				sumDep =sumDep+ sumRole;
				sb.append("The total hours of effiency in the " + departments.get(j).getRoles().get(k).getRoleName() + " role is: "
						+ sumRole + "\n");
				sumRole=0;
			}
			sb.append("The total hours of effiency in the " + departments.get(j).getDepName() + " department is: "
					+ sumDep + "\n");
			sumDep = 0;
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Company [companyName= " + companyName + ", dateOfFoundation="
				+ dateOfFoundation + ", numOfDepartments= " + numOfDepartments + ", departments=");
		for (int i = 0; i < numOfDepartments; i++) {
			sb.append(departments.get(i).toString());
		}
		return sb.toString();
	}

}
