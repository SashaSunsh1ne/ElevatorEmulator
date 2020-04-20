package model;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;
import java.util.Observer;

public class ElevatorController implements Observer {

    private Elevator elevator;
    private List<Integer> choosedFloors = new LinkedList<Integer>();
    private int maxChoosedFloors;
    private Text value;
    private List<Button> buttons = new LinkedList<Button>();
    private int timeState;

    public ElevatorController(Elevator elevator, int maxChoosedFloors, Text value) {
        this.elevator = elevator;
        this.maxChoosedFloors = maxChoosedFloors;
        this.value = value;
    }

    public void addButtonsToController(int minFloor, int countFloors, Pane elevatorControllerButtons) {
        for (int i = countFloors; i >= minFloor; i--) {
            Button button = new Button();
            button.setFont(new Font(35.0));
            button.setText(i + "");
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-border-radius: 50; -fx-background-radius: 50;");
            int temp = i;
            button.setOnAction(actionEventButton -> {
                this.addFloorToList(temp, button);
            } );
            buttons.add(button);
            elevatorControllerButtons.getChildren().add(button);
        }
        Button button = new Button();
        button.setFont(new Font(35.0));
        button.setText("ОТМЕНА");
        button.setPrefHeight(100);
        button.setPrefWidth(200);
        button.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-border-radius: 50; -fx-background-radius: 50;");
        button.setOnAction(actionEventButton -> {
            cancellation();
        } );
        elevatorControllerButtons.getChildren().add(button);
    }

    public void addFloorToList(int floor, Button button) {
        boolean flag = false;
        if (choosedFloors.size() < maxChoosedFloors) {
            for (int elevatorFloor : choosedFloors) {
                if (elevatorFloor == floor)
                    flag = true;
            }
            if (!flag) {
                choosedFloors.add(floor);
                Collections.sort(choosedFloors);
                button.setStyle("-fx-border-color: red; -fx-border-width: 5; -fx-border-radius: 50; -fx-background-radius: 50;");
            }
        }

    }

    public void deleteFloorFromList(Object floor) {
        choosedFloors.remove(floor);
        for (Button button : buttons) {
            if (Integer.parseInt(button.getText()) == (int)floor) {
                button.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-border-radius: 50; -fx-background-radius: 50;");
            }
        }
    }

    public void cancellation() {
        choosedFloors.clear();
        for (Button button : buttons) {
            button.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-border-radius: 50; -fx-background-radius: 50;");
        }
    }

    public void move() {
        int k = 0;
        int d = 0;
        for (int i : choosedFloors) {
            if (elevator.getCurFloor() < i)
                k++;
            if (k == choosedFloors.size())
                elevator.setUpward(true);
            if (elevator.getCurFloor() > i)
                d++;
            if (d == choosedFloors.size())
                elevator.setUpward(false);
        }
        if (timeState % 1 == 0) {
            if (!choosedFloors.isEmpty()) {
                elevator.move();
                Iterator<Integer> choosedFloorsIterator = choosedFloors.iterator();
                while (choosedFloorsIterator.hasNext()) {
                    if (choosedFloorsIterator.next() == elevator.getCurFloor()) {
                        for (Button button : buttons) {
                            if (Integer.parseInt(button.getText()) == elevator.getCurFloor()) {
                                button.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-border-radius: 50; -fx-background-radius: 50;");
                            }
                        }
                        deleteFloorFromList(elevator.getCurFloor());
                        choosedFloorsIterator = choosedFloors.iterator();
                    }
                }
            }
            value.setText(elevator.getCurFloor() + "");
        }
    }

    public void setTimeState(int timeState) {
        this.timeState = timeState;
    }

    @Override
    public void update(Observable o, Object timeState) {
        this.setTimeState((int)timeState);
        move();
    }
}
