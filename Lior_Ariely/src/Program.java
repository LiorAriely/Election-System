
import java.time.LocalDate;
import java.util.ArrayList;

import Controller.Controller;
import Model.BasicEmployee;
import Model.BonusEmployee;
import Model.Company;
import Model.Department;
import Model.Employee;
import Model.HourlyEmployee;
import Model.Model;
import Model.Role;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stg) throws Exception {
		Model model = new Model();
		View view = new View(stg);
		Controller controller = new Controller(model, view);

		ArrayList<Company> allCompanies = new ArrayList<Company>();
		allCompanies = model.getCompanies();
		allCompanies.add(new Company("2021-07-12", "Lux"));

		ArrayList<Department> departments = new ArrayList<Department>();
		departments.add(new Department("Engineers", allCompanies.get(0), false, false));
		departments.add(new Department("Maintenance", allCompanies.get(0), true, true));

		allCompanies.get(0).setDeps(departments);

		ArrayList<Role<Employee>> roles = new ArrayList<Role<Employee>>();
		roles.add(new Role("Cleaner", departments.get(1), false, true));
		roles.add(new Role("Plumber", departments.get(1), true, true));
		roles.add(new Role("Programmer", departments.get(0), true, true));
		roles.add(new Role("Data analyst", departments.get(0), true, true));

		allCompanies.get(0).setRoles(roles);

		departments.get(1).addRole(roles.get(0));
		departments.get(1).addRole(roles.get(1));
		departments.get(0).addRole(roles.get(2));
		departments.get(0).addRole(roles.get(3));

		ArrayList<Employee> employees = new ArrayList<Employee>();
		LocalDate date = LocalDate.parse("2002-01-11");
		employees.add(new HourlyEmployee("Gil Levi", date, "287498387", "Female", roles.get(0), null, 8));
		date = LocalDate.parse("1990-12-17");
		employees.add(new BasicEmployee("Dani Gal", date, "124763753", "Male", roles.get(1), null, 8));
		date = LocalDate.parse("1981-10-27");
		employees.add(new BonusEmployee("Shay Dean", date, "678438234", "Male", roles.get(2), null, 8));
		date = LocalDate.parse("2000-02-16");
		employees.add(new BonusEmployee("Dalia lily", date, "971667789", "Female", roles.get(3), null, 8));

		roles.get(0).addEmployee(employees.get(0));
		roles.get(1).addEmployee(employees.get(1));
		roles.get(2).addEmployee(employees.get(2));
		roles.get(3).addEmployee(employees.get(3));

		allCompanies.get(0).setEmployees(employees); 
	}
}
