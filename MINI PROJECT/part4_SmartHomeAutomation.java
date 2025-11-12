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
