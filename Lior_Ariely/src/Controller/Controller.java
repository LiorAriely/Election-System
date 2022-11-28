package Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Model.DateException;
import Model.DoubleNameException;
import Model.IdException;
import Model.Model;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {

	private Model model;
	private View view;

	public Controller(Model m, View v) {

		model = m;
		view = v;

		Alert alertDepName = new Alert(AlertType.ERROR);
		alertDepName.setTitle("Error");
		alertDepName.setHeaderText("");
		alertDepName.setContentText("name allready exits, Try again");
		alertDepName.getDialogPane().setPrefSize(200, 200);
		alertDepName.setResizable(true);

		Alert alertEmptyField = new Alert(AlertType.ERROR);
		alertEmptyField.setTitle("Error");
		alertEmptyField.setHeaderText("");
		alertEmptyField.setContentText("Empty field, Try again");
		alertEmptyField.getDialogPane().setPrefSize(200, 200);
		alertEmptyField.setResizable(true);

		Alert alertId = new Alert(AlertType.ERROR);
		alertId.setTitle("Error");
		alertId.setHeaderText("");
		alertId.setContentText("Invalid ID, Try again");
		alertId.getDialogPane().setPrefSize(200, 200);
		alertId.setResizable(true);

		Alert alertDate = new Alert(AlertType.ERROR);
		alertDate.setTitle("Error");
		alertDate.setHeaderText("");
		alertDate.setContentText("Invalid Date, Try again");
		alertDate.getDialogPane().setPrefSize(200, 200);
		alertDate.setResizable(true);

		Alert alertHour = new Alert(AlertType.ERROR);
		alertHour.setTitle("Error");
		alertHour.setHeaderText("");
		alertHour.setContentText("choose hours of preference");
		alertHour.getDialogPane().setPrefSize(200, 200);
		alertHour.setResizable(true);

		EventHandler<ActionEvent> switch2 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setSceneAddDep();
				view.setCmbComp(model.getCompanies(), view.getCmbCompanies());
			}
		};
		view.btn2Event(switch2);

		EventHandler<ActionEvent> switch3 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setSceneAddRole();
				view.setCmbDep(model.getDepartments(), view.getCmbDepartments());
			}
		};
		view.btn3Event(switch3);

		EventHandler<ActionEvent> switch4 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setSceneAddEmployee();
				view.setCmbRoles(model.getRoles(), view.getCmbRoles());
			}
		};
		view.btn4Event(switch4);

		EventHandler<ActionEvent> switch5 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setSceneChangeDep();
				view.setCmbChangableDeps(model.getDepartments(), view.getCmbDepsChange());
			}
		};
		view.btn7Event(switch5);

		EventHandler<ActionEvent> switch6 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setSceneChangeRole();
				view.setCmbChangableRoles(model.getRoles(), view.getCmbChangableRole());
			}
		};
		view.btn6Event(switch6);

		EventHandler<ActionEvent> switchBack = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.setSceneMenu();
				view.getCmbCompanies().getItems().clear();
				view.getCmbDepartments().getItems().clear();
				view.getCmbChangableRole().getItems().clear();
				view.getCmbDepsChange().getItems().clear();
				view.getCmbRoles().getItems().clear();
			}
		};
		// view.back1Event(switchBack);
		view.back2Event(switchBack);
		view.back3Event(switchBack);
		view.back4Event(switchBack);
		view.back5Event(switchBack);
		view.back6Event(switchBack);

		EventHandler<ActionEvent> addDep = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (view.getCmbCompanies().getValue() != null) {
					try {
						model.addDepartmant(view.getDepName(), view.getChangeDep(), view.getSyncDep());
						view.setSceneMenu();
						view.getCmbCompanies().getItems().clear();
						view.getCmbCompanies().setValue(null);
						view.getTf2().clear();
						view.getChkChangeDep().setSelected(false);
						view.getChkSyncDep().setSelected(false);
					} catch (DoubleNameException e) {
						alertDepName.showAndWait();
					} catch (Exception e) {
						alertEmptyField.showAndWait();
					}
				} else
					alertEmptyField.showAndWait();
			}
		};
		view.addDepEvent(addDep);

		EventHandler<ActionEvent> addRole = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (view.getCmbDepartments().getValue() != null) {
					try {
						model.addRole(view.getRoleName(), view.getCmbDepartments().getValue(), view.getChangeRole(),
								view.getSyncRole());
						view.setSceneMenu();
						view.getCmbDepartments().getItems().clear();
						view.getCmbDepartments().setValue(null);
						view.getTf3().clear();
						view.getChkSyncRole().setSelected(false);
						view.getChkChangeRole().setSelected(false);
					} catch (DoubleNameException e) {
						alertDepName.showAndWait();
					} catch (Exception e) {
						alertEmptyField.showAndWait();
					}
				} else
					alertEmptyField.showAndWait();
			}

		};
		view.addRoleEvent(addRole);

		EventHandler<ActionEvent> addEmployee = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (view.getEmployeeName() != "" && view.getId() != "" && view.getDate() != null
						&& view.getGender() != "" && view.getEmployeeRole() != "" && view.getEmployeeType() != ""
						&& view.getPref() != "" && view.getHout() != -1) {
					if (view.getPref().equals("Start Early") || view.getPref().equals("Start Late")) {
						try {
							model.addEmployee(view.getEmployeeName(), view.getDate(), view.getId(), view.getGender(),
									view.getEmployeeRole(), view.getEmployeeType(), view.getPref(), view.getHout());
							view.setSceneMenu();
							view.getCmbRoles().getItems().clear();
							view.getTf4().clear();
							view.getTf5().clear();
							view.getDatePicker2().setValue(null);
							view.getCmbEmployeeType().setValue(null);
							view.getCmbGender().setValue(null);
							view.getCmbHours().setValue(null);
							view.getCmbPref().setValue(null);
						} catch (IdException ie) {
							alertId.showAndWait();
						} catch (DateException de) {
							alertDate.showAndWait();
						}
					} else
						try {
							model.addEmployee(view.getEmployeeName(), view.getDate(), view.getId(), view.getGender(),
									view.getEmployeeRole(), view.getEmployeeType(), view.getPref(), 8);
							view.setSceneMenu();
						} catch (IdException ie) {
							alertId.showAndWait();
						} catch (DateException de) {
							alertDate.showAndWait();
						}
				} else
					alertEmptyField.showAndWait();
			}
		};
		view.addEmployeeEvent(addEmployee);

		EventHandler<ActionEvent> PresentEfficiency = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alertPresentEfc = new Alert(AlertType.INFORMATION);
				alertPresentEfc.setTitle("Efficiency");
				alertPresentEfc.setHeaderText("");
				alertPresentEfc.setContentText(model.presentEfficiency());
				alertPresentEfc.getDialogPane().setPrefSize(1500, 500);
				alertPresentEfc.setResizable(true);
				alertPresentEfc.showAndWait();
			}
		};
		view.btn8Event(PresentEfficiency);

		EventHandler<ActionEvent> PresentAll = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alertPresent = new Alert(AlertType.INFORMATION);
				alertPresent.setTitle("All details");
				alertPresent.setHeaderText("");
				alertPresent.setContentText(model.presentAll());
				alertPresent.getDialogPane().setPrefSize(1500, 500);
				alertPresent.setResizable(true);
				alertPresent.showAndWait();
			}
		};
		view.btn5Event(PresentAll);

		EventHandler<ActionEvent> ChangeDep = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (view.getDepNameChange() != "" && view.getChangeHourDep() != -1) {
					model.changeDep(view.getDepNameChange(), view.getSyncAllDeps(), view.getChangeHourDep());
					view.setSceneMenu();
					view.getCmbDepsChange().getItems().clear();
					view.getCmbNewHour1().setValue(null);
					view.getChkSync1().setSelected(false);
				} else
					alertEmptyField.showAndWait();
			}
		};
		view.changeDep(ChangeDep);

		EventHandler<ActionEvent> ChangeRole = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (view.getRoleNameChange() != "" && view.getChangeHourRole() != -1) {
					model.changeRole(view.getRoleNameChange(), view.getSyncAllRoles(), view.getChangeHourRole());
					view.setSceneMenu();
					view.getCmbChangableRole().getItems().clear();
					view.getCmbNewHour2().setValue(null);
					view.getChkSync2().setSelected(false);
				} else
					alertEmptyField.showAndWait();
			}
		};
		view.changeRole(ChangeRole);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			public void run() {
				ObjectOutputStream outFile = null;
				try {
					outFile = new ObjectOutputStream(new FileOutputStream("company.data"));

				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
					e1.printStackTrace();
				} catch (IOException e1) {
					System.out.println("Input Output exception");
					e1.printStackTrace();
				}
				try {
					model.saveAll(outFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
	}
}
