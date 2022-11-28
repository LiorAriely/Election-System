package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
	private ArrayList<Company> companies;
	private int numOfcompanies;

	public Model() {
		companies = new ArrayList<Company>();
		numOfcompanies = 1; // set to 1 because of hardcode
		ObjectInputStream inFile = null;
		try {
			inFile = new ObjectInputStream(new FileInputStream("company.data"));
			readAll(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveAll(ObjectOutputStream outFile) throws IOException {
		outFile.writeObject(companies.get(numOfcompanies - 1));
		outFile.close();
	}

	public void readAll(ObjectInputStream inFile) throws IOException, ClassNotFoundException {
		companies.add((Company) inFile.readObject());
		inFile.close();
	}

	public String presentEfficiency() {
		return companies.get(numOfcompanies - 1).efficiency();
	}

	public String presentAll() {
		return companies.get(numOfcompanies - 1).toString();
	}

	public void addEmployee(String name, LocalDate date, String Id, String gender, String role, String type, String p,
			int time) throws IdException, DateException {
		companies.get(numOfcompanies - 1).addEmployee(name, date, Id, gender, role, type, p, time);
	}

	public void addRole(String name, String dep, boolean c, boolean s) throws DoubleNameException {
		companies.get(numOfcompanies - 1).addRole(name, dep, c, s);
	}

	public void addDepartmant(String DepName, boolean change, boolean sync) throws DoubleNameException {
		companies.get(numOfcompanies - 1).addDepartment(DepName, companies.get(numOfcompanies - 1), change, sync);
	}

	public void changeDep(String name, boolean sync, int newTime) {
		companies.get(numOfcompanies - 1).changeDepmethod(name, sync, newTime);
	}

	public void changeRole(String name, boolean sync, int newTime) {
		companies.get(numOfcompanies - 1).changeRoleMethod(name, sync, newTime);
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public ArrayList<Department> getDepartments() {
		return companies.get(numOfcompanies - 1).getDepartments();
	}

	public ArrayList<Role<Employee>> getRoles() {
		return companies.get(numOfcompanies - 1).getAllRoles();
	}
}
