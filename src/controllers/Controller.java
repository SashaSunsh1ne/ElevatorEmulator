package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Button startButton;
    public FlowPane elevatorControllerButtons;
    public ChoiceBox choiceBox;
    public ChoiceBox choiceBox1;
    public Text value;

    private Elevator elevator;
    private ElevatorController elevatorController;
    private TimeServer observable = new TimeServer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(5, 9, 10, 17, 20);
        choiceBox.setValue(5);
        choiceBox1.getItems().addAll(1, 0, -1, -2, -3);
        choiceBox1.setValue(1);
        startButton.setOnAction(actionEvent ->  {
            observable.detachAll();
            elevator = new Elevator((int)choiceBox1.getValue(), (int)choiceBox.getValue());
            elevatorController = new ElevatorController(elevator, 3, value);
            observable.attach(elevatorController);
            elevatorControllerButtons.getChildren().clear();
            elevatorController.addButtonsToController(elevator.getMinFloor(), (int)choiceBox.getValue(), elevatorControllerButtons);
        } );
    }
}
