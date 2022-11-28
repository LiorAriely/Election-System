package View;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.Company;
import Model.Department;
import Model.Employee;
import Model.Role;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	private Stage stg;
	private DatePicker datePicker2;
	private Scene sceneMenu, sceneAddEmployee, sceneAddCompany, sceneAddDepartment, sceneAddRole, sceneChangeMethodRole,
			sceneChangeMethodDep;
	private Button btn2, btn3, btn4, btn5, btn6, btn7, btn8, addEmployee, addDep, addRole, saveChanges1, saveChanges2,
			back2, back3, back4, back5, back6;
	private Group root1, root3, root4, root5, root6, root7;
	private VBox vDepName, vRoleName, vEmployeeName, vID;
	private Label lblDepName, lblRoleName, lblEmployeeName, lblID, lblDateBirth, lblGender, lblRole, lblDep,
			lblEmployeeType, lblComp, lblPref, lblHours, lblDeps, lblChange1, lblRoles, lblChange2;
	private CheckBox chkChangeDep, chkSyncDep, chkChangeRole, chkSyncRole, chkSync1, chkSync2;
	private TextField tf2, tf3, tf4, tf5;
	private ComboBox<String> cmbGender, cmbRoles, cmbEmployeeType, cmbDepartments, cmbCompanies, cmbPref, cmbHours,
			cmbChangableDep, cmbChangableRole, cmbNewHour1, cmbNewHour2;

	public View(Stage stg) {

		root1 = new Group();
		sceneMenu = new Scene(root1, 500, 500);
		sceneMenu.setFill(Color.WHITESMOKE);
		btn2 = new Button("Add a new Department");
		btn2.setTranslateX(178);
		btn2.setTranslateY(85);
		btn3 = new Button("Add a new Role");
		btn3.setTranslateX(200);
		btn3.setTranslateY(120);
		btn4 = new Button("Add a new Employee");
		btn4.setTranslateX(185);
		btn4.setTranslateY(155);
		btn5 = new Button("Present all");
		btn5.setTranslateX(212);
		btn5.setTranslateY(190);
		btn6 = new Button("Change Method by Role");
		btn6.setTranslateX(178);
		btn6.setTranslateY(225);
		btn7 = new Button("Change Method by Department");
		btn7.setTranslateX(157);
		btn7.setTranslateY(260);
		btn8 = new Button("Present efficiency");
		btn8.setTranslateX(195);
		btn8.setTranslateY(295);
		root1.getChildren().addAll(btn2, btn3, btn4, btn5, btn6, btn7, btn8);

		// Add Department scene
		root3 = new Group();
		sceneAddDepartment = new Scene(root3, 500, 500);
		sceneAddDepartment.setFill(Color.WHITESMOKE);
		vDepName = new VBox();
		lblDepName = new Label("Name of department: ");
		tf2 = new TextField();
		vDepName.getChildren().addAll(lblDepName, tf2);
		vDepName.setTranslateX(30);
		vDepName.setTranslateY(50);

		lblComp = new Label("Company the department belongs to: ");
		lblComp.setTranslateX(30);
		lblComp.setTranslateY(100);
		cmbCompanies = new ComboBox<String>();
		cmbCompanies.setTranslateX(30);
		cmbCompanies.setTranslateY(120);

		chkChangeDep = new CheckBox("Is the method of this department changable? (if yes-mark here)");
		chkChangeDep.setLayoutX(30);
		chkChangeDep.setLayoutY(160);

		chkSyncDep = new CheckBox("Is the department syncable? (if yes-mark here)");
		chkSyncDep.setLayoutX(30);
		chkSyncDep.setLayoutY(190);

		back2 = new Button("Return to menu (without saving)");
		back2.setLayoutX(30);
		back2.setLayoutY(220);

		addDep = new Button("Add new Department");
		addDep.setLayoutX(260);
		addDep.setLayoutY(220);

		root3.getChildren().addAll(vDepName, back2, addDep, cmbCompanies, lblComp, chkChangeDep, chkSyncDep);

		// Add role scene
		root4 = new Group();
		sceneAddRole = new Scene(root4, 500, 500);
		sceneAddRole.setFill(Color.WHITESMOKE);
		vRoleName = new VBox();
		lblRoleName = new Label("Name of Role: ");
		tf3 = new TextField();
		vRoleName.getChildren().addAll(lblRoleName, tf3);
		vRoleName.setTranslateX(30);
		vRoleName.setTranslateY(50);

		lblDep = new Label("Department of role: ");
		lblDep.setTranslateX(30);
		lblDep.setTranslateY(100);
		cmbDepartments = new ComboBox<String>();
		cmbDepartments.setTranslateX(30);
		cmbDepartments.setTranslateY(120);

		chkChangeRole = new CheckBox("Is the method of this role changable? (if yes-mark here)");
		chkChangeRole.setTranslateX(30);
		chkChangeRole.setTranslateY(150);

		chkSyncRole = new CheckBox("Is the role syncable? (if yes-mark here)");
		chkSyncRole.setTranslateX(30);
		chkSyncRole.setTranslateY(180);

		back3 = new Button("Return to menu (without saving)");
		back3.setLayoutX(30);
		back3.setLayoutY(220);

		addRole = new Button("Add new Role");
		addRole.setTranslateX(260);
		addRole.setTranslateY(220);

		root4.getChildren().addAll(vRoleName, addRole, back3, lblDep, cmbDepartments, chkChangeRole, chkSyncRole);

		// Add Employee scene
		root5 = new Group();
		sceneAddEmployee = new Scene(root5, 500, 500);
		sceneAddEmployee.setFill(Color.WHITESMOKE);

		vEmployeeName = new VBox();
		lblEmployeeName = new Label("Name of Employee");
		tf4 = new TextField();
		vEmployeeName.getChildren().addAll(lblEmployeeName, tf4);
		vEmployeeName.setTranslateX(30);
		vEmployeeName.setTranslateY(50);

		vID = new VBox();
		lblID = new Label("Insert employee's ID: (format: 9 digits) ");
		tf5 = new TextField();
		vID.getChildren().addAll(lblID, tf5);
		vID.setTranslateX(30);
		vID.setTranslateY(100);

		lblDateBirth = new Label("Insert date of birth: (format: /day-month-year/ ) ");
		lblDateBirth.setLayoutX(30);
		lblDateBirth.setLayoutY(150);
		datePicker2 = new DatePicker();
		datePicker2.setLayoutX(30);
		datePicker2.setLayoutY(170);

		lblGender = new Label("Gender of Employee: ");
		lblGender.setTranslateX(30);
		lblGender.setTranslateY(200);
		cmbGender = new ComboBox<String>();
		cmbGender.setTranslateX(30);
		cmbGender.setTranslateY(215);
		cmbGender.getItems().addAll("Female", "Male", "Other");

		lblRole = new Label("Role of Employee: ");
		lblRole.setTranslateX(30);
		lblRole.setTranslateY(245);
		cmbRoles = new ComboBox<String>();
		cmbRoles.setTranslateX(30);
		cmbRoles.setTranslateY(260);

		lblEmployeeType = new Label("Employee type:");
		lblEmployeeType.setTranslateX(30);
		lblEmployeeType.setTranslateY(290);
		cmbEmployeeType = new ComboBox<String>();
		cmbEmployeeType.setTranslateX(30);
		cmbEmployeeType.setTranslateY(305);
		cmbEmployeeType.getItems().addAll("Basic Employee", "Bonus Employee", "Hourly Employee");

		lblPref = new Label("Choose the employee's preference: ");
		lblPref.setTranslateX(30);
		lblPref.setTranslateY(340);
		cmbPref = new ComboBox<String>();
		cmbPref.setTranslateX(30);
		cmbPref.setTranslateY(355);
		cmbPref.getItems().addAll("Start Early", "Start Late", "Home", "No Change");

		lblHours = new Label("What hour whould the employee want to start?");
		lblHours.setTranslateX(30);
		lblHours.setTranslateY(385);
		cmbHours = new ComboBox<String>();
		cmbHours.setTranslateX(30);
		cmbHours.setTranslateY(400);
		cmbHours.getItems().addAll("5", "6", "7", "8", "10", "11", "12", "13", "14");

		back4 = new Button("Return to menu (without saving)");
		back4.setLayoutX(30);
		back4.setLayoutY(450);

		addEmployee = new Button("Add new Employee");
		addEmployee.setTranslateX(260);
		addEmployee.setTranslateY(450);

		root5.getChildren().addAll(vEmployeeName, vID, lblDateBirth, datePicker2, lblGender, cmbGender, lblEmployeeType,
				cmbEmployeeType, lblRole, cmbRoles, back4, addEmployee, lblPref, lblHours, cmbPref, cmbHours);

		// Change method of department scene
		root6 = new Group();
		sceneChangeMethodDep = new Scene(root6, 500, 500);
		sceneChangeMethodDep.setFill(Color.WHITESMOKE);

		lblDeps = new Label("Which department whould you like to change? ");
		lblDeps.setTranslateX(30);
		lblDeps.setTranslateY(30);

		cmbChangableDep = new ComboBox<String>();
		cmbChangableDep.setTranslateX(30);
		cmbChangableDep.setTranslateY(55);

		lblChange1 = new Label("What is the new start hour? ");
		lblChange1.setTranslateX(30);
		lblChange1.setTranslateY(90);

		cmbNewHour1 = new ComboBox<String>();
		cmbNewHour1.setTranslateX(30);
		cmbNewHour1.setTranslateY(110);
		cmbNewHour1.getItems().addAll("5", "6", "7", "8", "10", "11", "12", "13", "14");

		chkSync1 = new CheckBox("Whould you like to sync all departments?");
		chkSync1.setTranslateX(30);
		chkSync1.setTranslateY(150);

		back5 = new Button("Return to menu (without saving)");
		back5.setLayoutX(30);
		back5.setLayoutY(300);

		saveChanges1 = new Button("Save changes");
		saveChanges1.setTranslateX(300);
		saveChanges1.setTranslateY(300);

		root6.getChildren().addAll(back5, lblDeps, cmbChangableDep, lblChange1, cmbNewHour1, chkSync1, saveChanges1);

		// Change method of Role
		root7 = new Group();
		sceneChangeMethodRole = new Scene(root7, 500, 500);
		sceneChangeMethodRole.setFill(Color.WHITESMOKE);

		lblRoles = new Label("Which Role whould you like to change? ");
		lblRoles.setTranslateX(30);
		lblRoles.setTranslateY(30);

		cmbChangableRole = new ComboBox<String>();
		cmbChangableRole.setTranslateX(30);
		cmbChangableRole.setTranslateY(55);

		lblChange2 = new Label("What is the new start hour? ");
		lblChange2.setTranslateX(30);
		lblChange2.setTranslateY(90);

		cmbNewHour2 = new ComboBox<String>();
		cmbNewHour2.setTranslateX(30);
		cmbNewHour2.setTranslateY(110);
		cmbNewHour2.getItems().addAll("5", "6", "7", "8", "10", "11", "12", "13", "14");

		chkSync2 = new CheckBox("Whould you like to sync all employees in role?");
		chkSync2.setTranslateX(30);
		chkSync2.setTranslateY(150);

		back6 = new Button("Return to menu (without saving)");
		back6.setLayoutX(30);
		back6.setLayoutY(300);

		saveChanges2 = new Button("Save changes");
		saveChanges2.setTranslateX(300);
		saveChanges2.setTranslateY(300);

		root7.getChildren().addAll(back6, lblRoles, cmbChangableRole, lblChange2, cmbNewHour2, chkSync2, saveChanges2);

		this.stg = stg;
		stg.setScene(sceneMenu);
		stg.setTitle("Work Space");
		stg.show();
	}

	public void btn2Event(EventHandler<ActionEvent> event) {
		btn2.setOnAction(event);
	}

	public void btn3Event(EventHandler<ActionEvent> event) {
		btn3.setOnAction(event);
	}

	public void btn4Event(EventHandler<ActionEvent> event) {
		btn4.setOnAction(event);
	}

	public void btn5Event(EventHandler<ActionEvent> event) {
		btn5.setOnAction(event);
	}

	public void btn7Event(EventHandler<ActionEvent> event) {
		btn7.setOnAction(event);
	}

	public void btn6Event(EventHandler<ActionEvent> event) {
		btn6.setOnAction(event);
	}

	public void btn8Event(EventHandler<ActionEvent> event) {
		btn8.setOnAction(event);
	}

	public void back2Event(EventHandler<ActionEvent> event) {
		back2.setOnAction(event);
	}

	public void back3Event(EventHandler<ActionEvent> event) {
		back3.setOnAction(event);
	}

	public void back4Event(EventHandler<ActionEvent> event) {
		back4.setOnAction(event);
	}

	public void back5Event(EventHandler<ActionEvent> event) {
		back5.setOnAction(event);
	}

	public void back6Event(EventHandler<ActionEvent> event) {
		back6.setOnAction(event);
	}

	public void addDepEvent(EventHandler<ActionEvent> event) {
		addDep.setOnAction(event);
	}

	public void addRoleEvent(EventHandler<ActionEvent> event) {
		addRole.setOnAction(event);
	}

	public void addEmployeeEvent(EventHandler<ActionEvent> event) {
		addEmployee.setOnAction(event);
	}

	public void changeDep(EventHandler<ActionEvent> event) {
		saveChanges1.setOnAction(event);
	}

	public void changeRole(EventHandler<ActionEvent> event) {
		saveChanges2.setOnAction(event);
	}

	public void setSceneAddCompany() {
		stg.setScene(sceneAddCompany);
	}

	public void setSceneAddDep() {
		stg.setScene(sceneAddDepartment);
	}

	public void setSceneAddRole() {
		stg.setScene(sceneAddRole);
	}

	public void setSceneAddEmployee() {
		stg.setScene(sceneAddEmployee);
	}

	public void setSceneMenu() {
		stg.setScene(sceneMenu);
	}

	public void setSceneChangeDep() {
		stg.setScene(sceneChangeMethodDep);
	}

	public void setSceneChangeRole() {
		stg.setScene(sceneChangeMethodRole);
	}

	public void setCmbComp(ArrayList<Company> companies, ComboBox<String> c) {
		int i;
		for (i = 0; i < companies.size(); i++) {
			c.getItems().add(companies.get(i).getCompanyName());
		}
	}

	public void setCmbDep(ArrayList<Department> departments, ComboBox<String> c) {
		int j;
		for (j = 0; j < departments.size(); j++) {
			c.getItems().add(departments.get(j).getDepName());
		}
	}

	public void setCmbChangableDeps(ArrayList<Department> departments, ComboBox<String> c) {
		int i;
		for (i = 0; i < departments.size(); i++) {
			if (departments.get(i).isChangable() == true) {
				c.getItems().add(departments.get(i).getDepName());
			}
		}
	}

	public void setCmbChangableRoles(ArrayList<Role<Employee>> roles, ComboBox<String> c) {
		int i;
		for (i = 0; i < roles.size(); i++) {
			if (roles.get(i).getDep().isSync() == false) {
				if (roles.get(i).isChangable() == true)
					c.getItems().add(roles.get(i).getRoleName());
			}
		}
	}

	public void setCmbRoles(ArrayList<Role<Employee>> roles, ComboBox<String> c) {
		int i;
		for (i = 0; i < roles.size(); i++) {
			c.getItems().add(roles.get(i).getRoleName());
		}
	}

	public ComboBox<String> getCmbChangableRole() {
		return cmbChangableRole;
	}

	public ComboBox<String> getCmbDepsChange() {
		return cmbChangableDep;
	}

	public ComboBox<String> getCmbCompanies() {
		return cmbCompanies;
	}

	public ComboBox<String> getCmbDepartments() {
		return cmbDepartments;
	}

	public String getDepName() throws Exception {
		String st = tf2.getText();
		if (!st.isEmpty())
			return st;
		else
			throw new Exception();
	}

	public String getRoleName() throws Exception {
		String st = tf3.getText();
		if (!st.isEmpty())
			return st;
		else
			throw new Exception();
	}

	public TextField getTf3() {
		return tf3;
	}

	public ComboBox<String> getCmbRoles() {
		return cmbRoles;
	}

	public String getEmployeeName() {
		return tf4.getText();
	}

	public String getId() {
		return tf5.getText();
	}

	public LocalDate getDate() {
		return datePicker2.getValue();
	}

	public String getGender() {
		return cmbGender.getValue();
	}

	public String getEmployeeRole() {
		return cmbRoles.getValue();
	}

	public String getEmployeeType() {
		return cmbEmployeeType.getValue();
	}

	public TextField getTf4() {
		return tf4;
	}

	public TextField getTf5() {
		return tf5;
	}

	public DatePicker getDatePicker2() {
		return datePicker2;
	}

	public ComboBox<String> getCmbGender() {
		return cmbGender;
	}

	public ComboBox<String> getCmbEmployeeType() {
		return cmbEmployeeType;
	}

	public boolean getChangeDep() {
		if (chkChangeDep.isSelected())
			return true;
		return false;
	}

	public boolean getSyncDep() {
		if (chkSyncDep.isSelected())
			return true;
		return false;
	}

	public boolean getSyncRole() {
		if (chkSyncRole.isSelected())
			return true;
		return false;
	}

	public boolean getChangeRole() {
		if (chkChangeRole.isSelected())
			return true;
		return false;
	}

	public String getPref() {
		return cmbPref.getValue();
	}

	public int getHout() {
		int time;
		String st = "";
		st = cmbHours.getValue();
		if (st == null)
			return -1;
		time = Integer.parseInt(st);
		return time;
	}

	public String getDepNameChange() {
		return cmbChangableDep.getValue();
	}

	public boolean getSyncAllDeps() {
		if (chkSync1.isSelected())
			return true;
		return false;
	}

	public int getChangeHourDep() {
		int h;
		String st = "";
		st = cmbNewHour1.getValue();
		if (st == null)
			return -1;
		h = Integer.parseInt(st);
		return h;
	}

	public String getRoleNameChange() {
		return cmbChangableRole.getValue();
	}

	public TextField getTf2() {
		return tf2;
	}

	public boolean getSyncAllRoles() {
		if (chkSync2.isSelected())
			return true;
		return false;
	}

	public int getChangeHourRole() {
		int h;
		String st = "";
		st = cmbNewHour2.getValue();
		if (st == null)
			return -1;
		h = Integer.parseInt(cmbNewHour2.getValue());
		return h;
	}

	public CheckBox getChkChangeDep() {
		return chkChangeDep;
	}

	public CheckBox getChkSyncDep() {
		return chkSyncDep;
	}

	public CheckBox getChkChangeRole() {
		return chkChangeRole;
	}

	public CheckBox getChkSyncRole() {
		return chkSyncRole;
	}

	public ComboBox<String> getCmbHours() {
		return cmbHours;
	}

	public ComboBox<String> getCmbPref() {
		return cmbPref;
	}

	public CheckBox getChkSync1() {
		return chkSync1;
	}

	public CheckBox getChkSync2() {
		return chkSync2;
	}

	public ComboBox<String> getCmbNewHour1() {
		return cmbNewHour1;
	}

	public ComboBox<String> getCmbNewHour2() {
		return cmbNewHour2;
	}

}
