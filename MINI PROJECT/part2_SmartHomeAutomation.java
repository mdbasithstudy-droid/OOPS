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
