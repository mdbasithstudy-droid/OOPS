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
