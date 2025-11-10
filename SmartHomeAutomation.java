//javafxcompile SmartHomeAutomation.java
//javafxrun SmartHomeAutomation

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SmartHomeAutomation extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Home Automation System");

        // ====== MAIN GRID SETUP ======
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(25);

        // ====== HEADER ======
        Label header = new Label("🏠 Smart Home Dashboard");
        header.setFont(Font.font("Arial", 22));
        header.setTextFill(Color.DARKBLUE);

        // ====== DEVICE CONTROLS ======
        Label lightsLabel = new Label("Lights:");
        ToggleButton lightsToggle = new ToggleButton("OFF");

        Label fanLabel = new Label("Fan:");
        ToggleButton fanToggle = new ToggleButton("OFF");

        Label acLabel = new Label("AC:");
        ToggleButton acToggle = new ToggleButton("OFF");

        Label doorLabel = new Label("Door Lock:");
        ToggleButton doorToggle = new ToggleButton("Locked");

        // ====== STATUS AREA ======
        Label statusLabel = new Label("System ready.");
        statusLabel.setTextFill(Color.DARKSLATEGRAY);
        statusLabel.setFont(Font.font("Arial", 14));

        TextArea dashboard = new TextArea();
        dashboard.setEditable(false);
        dashboard.setPrefHeight(120);
        dashboard.setFont(Font.font("Consolas", 14));
        dashboard.setText("Lights: OFF\nFan: OFF\nAC: OFF\nDoor: Locked");

        // ====== EVENT HANDLERS ======
        lightsToggle.setOnAction(e -> {
            if (lightsToggle.isSelected()) {
                lightsToggle.setText("ON");
                lightsToggle.setStyle("-fx-background-color: lightgreen;");
                statusLabel.setText("Lights turned ON.");
            } else {
                lightsToggle.setText("OFF");
                lightsToggle.setStyle("-fx-background-color: lightcoral;");
                statusLabel.setText("Lights turned OFF.");
            }
            updateDashboard(dashboard, lightsToggle, fanToggle, acToggle, doorToggle);
        });

        fanToggle.setOnAction(e -> {
            if (fanToggle.isSelected()) {
                fanToggle.setText("ON");
                fanToggle.setStyle("-fx-background-color: lightgreen;");
                statusLabel.setText("Fan turned ON.");
            } else {
                fanToggle.setText("OFF");
                fanToggle.setStyle("-fx-background-color: lightcoral;");
                statusLabel.setText("Fan turned OFF.");
            }
            updateDashboard(dashboard, lightsToggle, fanToggle, acToggle, doorToggle);
        });

        acToggle.setOnAction(e -> {
            if (acToggle.isSelected()) {
                acToggle.setText("ON");
                acToggle.setStyle("-fx-background-color: lightgreen;");
                statusLabel.setText("Air Conditioner turned ON.");
            } else {
                acToggle.setText("OFF");
                acToggle.setStyle("-fx-background-color: lightcoral;");
                statusLabel.setText("Air Conditioner turned OFF.");
            }
            updateDashboard(dashboard, lightsToggle, fanToggle, acToggle, doorToggle);
        });

        doorToggle.setOnAction(e -> {
            if (doorToggle.isSelected()) {
                doorToggle.setText("Unlocked");
                doorToggle.setStyle("-fx-background-color: lightgreen;");
                statusLabel.setText("Door Unlocked.");
            } else {
                doorToggle.setText("Locked");
                doorToggle.setStyle("-fx-background-color: lightcoral;");
                statusLabel.setText("Door Locked.");
            }
            updateDashboard(dashboard, lightsToggle, fanToggle, acToggle, doorToggle);
        });

        // ====== CONTROL BUTTONS ======
        Button offAllButton = new Button("Turn Off All");
        offAllButton.setOnAction(e -> {
            lightsToggle.setSelected(false);
            fanToggle.setSelected(false);
            acToggle.setSelected(false);
            doorToggle.setSelected(false);
            resetButtonStyle(lightsToggle, fanToggle, acToggle, doorToggle);
            updateDashboard(dashboard, lightsToggle, fanToggle, acToggle, doorToggle);
            statusLabel.setText("All systems turned OFF and door locked.");
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        // ====== LAYOUT DESIGN ======
        VBox deviceBox = new VBox(12,
                createRow(lightsLabel, lightsToggle),
                createRow(fanLabel, fanToggle),
                createRow(acLabel, acToggle),
                createRow(doorLabel, doorToggle)
        );
        deviceBox.setPadding(new Insets(10));
        deviceBox.setStyle("-fx-border-color: gray; -fx-border-radius: 10; -fx-border-width: 2;");
        deviceBox.setAlignment(Pos.CENTER_LEFT);

        HBox buttonsBox = new HBox(15, offAllButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox mainLayout = new VBox(20, header, deviceBox, statusLabel, dashboard, buttonsBox);
        mainLayout.setPadding(new Insets(20));

        Scene scene = new Scene(mainLayout, 420, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ====== Helper to Update Dashboard ======
    private void updateDashboard(TextArea dashboard, ToggleButton lights, ToggleButton fan,
                                 ToggleButton ac, ToggleButton door) {
        dashboard.setText(
                "Lights: " + lights.getText() +
                "\nFan: " + fan.getText() +
                "\nAC: " + ac.getText() +
                "\nDoor: " + door.getText()
        );
    }

    // ====== Helper to Reset Styles ======
    private void resetButtonStyle(ToggleButton... buttons) {
        for (ToggleButton btn : buttons) {
            btn.setText(btn == buttons[3] ? "Locked" : "OFF");
            btn.setStyle("-fx-background-color: lightcoral;");
        }
    }

    // ====== Helper to Create Rows ======
    private HBox createRow(Label label, ToggleButton toggle) {
        HBox row = new HBox(30, label, toggle);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
