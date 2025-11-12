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
